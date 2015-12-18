<#import "/spring.ftl" as spring/>
<#import "include/layout.ftl" as layout> 

<@layout.header "Segmentation", "segementation page">

    <!-- dhtmlx.css contains styles definitions for all use components -->
    <link rel="STYLESHEET" href="js/dhtmlx/dhtmlx.css" type="text/css" media="screen">
    
    <!-- dhtmlx.js contains all necessary dhtmlx library javascript code -->
    <script src="js/dhtmlx/dhtmlx.js"></script>
        
	<!-- include special code for these examples which provides images -->
	<script src="js/fabric/fabric.min.js"></script>
	
	<script src="js/fabric/fabricjs_viewport.js"></script>

    <style>
        /*these styles allow dhtmlxLayout to work in fullscreen mode in different browsers correctly*/
        html, body {
            width: 100%;
            height: 100%;
            margin: 0px;
            overflow: hidden;
            background-color:grey;
        }
		
		div .segementation_window {
			position: relative;
			width: 100%;
			height: 100%;
			overflow: auto;
			background-color:#EEEEEE;
		}
		
		canvas {
			position: relative;
			width: 100%;
			height: 100%;
			overflow: auto;
		}
		
    </style>  

   <script type="text/javascript">
        var layout,menu,toolbar,contactsGrid,contactForm;
		var segemationLayout;
		var seriesObject; //the series images that is under the contouring. It includes the control functions to display the proper informations inside the view and which pages should be displayed at the current
		
		var pixelSpacingX = 0.6640625; //unit is mm 
		var pixelSpacingY = 0.6640625; //unit is mm
		var pixelSpacingSlice = 1; // unit is: mm
		var totalFrames = 394; // the total number of the frames, which can be retrieved from the DICOM header
		var rows = 512; //the value from (0028,0010) Rows   
		var columns = 512; //the value from 
		
		function viewPosTransform(sourceViewMode, targetViewMode, viewPos)
		{
			if ("T"===sourceViewMode)
			{
				//Transvers to others
				if ("S"===targetViewMode)
				{
					return {
						x: viewPos.y, 
						y: viewPos.frame,
						frame: viewPos.x
					} 
				}
				else ("C"===targetViewMode)
				{
					return {
						x: viewPos.x, 
						y: viewPos.frame,
						frame: viewPos.y
					} 				
				}
			}
			else if ("S")
			{
				//Sagittal to others
				if ("T"===targetViewMode)
				{
					return {
						x: viewPos.y, 
						y: viewPos.frame,
						frame: viewPos.y
					} 						
				}
				else ("C"===targetViewMode)
				{
					return {
						x: viewPos.frame, 
						y: viewPos.y,
						frame: viewPos.x
					} 						
				}
			}
			else //C
			{
				//Coronal to others
				if ("S"===targetViewMode)
				{
					return {
						x: viewPos.frame, 
						y: viewPos.y,
						sliceNo: viewPos.x
					} 						
				}
				else ("T"===targetViewMode)
				{
					return {
						x: viewPos.x, 
						y: viewPos.frame,
						frame: viewPos.y
					} 						
				}
			}
		}


				 
        dhtmlx.image_path = "js/dhtmlx/imgs/";
        dhtmlxEvent(window,"load",function(){   
        
        	
             //provides the script as a handler of the 'onload' HTML event
            //layout = new dhtmlXLayoutObject(document.body,"3W");                       //initializes dhtmlxLayout
			layout = new dhtmlXLayoutObject(document.body,"4A");                       //initializes dhtmlxLayout
            layout.cells("a").setText("Patient Information");                                     //sets the text in the header of the 'grid' column
			layout.cells("a").setWidth($(window).width() * 0.15);
			//layout.cells("a").setHeight(layout.getHeight()/2)
			layout.cells("a").fixSize(false, false);



			//load the sample view:
			//patientInformationGrid = layout.cells("a").attachGrid();
			//patientInformationGrid.setHeader("Description,Value");
			//patientInformationGrid.load("data/patientinfo.json",function(){alert("Data has been loaded.")},"json");
			//patientInformationGrid.parse(data,"json"); //takes the name and format of the data source
			
            layout.cells("b").setText("Structure");                              //sets the text in the header of the 'form' column
			//layout.cells("b").setHeight(300);
			
			layout.cells("c").hideHeader();			
            layout.cells("c").setWidth($(window).width() * 0.7);                                           //sets the width of the 'form' column


			layout.cells("d").hideHeader();			
            //layout.cells("d").setWidth(1300);                                           //sets the width of the 'form' column

			
			//Top menu and the right left tools
	        toolbar1 = layout.attachToolbar();                                          //initializes dhtmlxToolbar
            toolbar1.setIconsPath("icons/");                                            //sets the path to custom images
            toolbar1.loadStruct("data/toolbar.xml");                                       //loads items from the "data/toolbar.xml" file to the toolbar

	
		leftMenu = layout.cells("d").attachDataView({
		type: {
			template: "<div class='menu_item #id#'>"+
					"<div class='menu_item_text'>#text#</div>"+
				"</div>",
			margin: 5,
			padding: 5,
			height: 68,
			width:68
		},
		drag: false,
		select: true
	});
	
	leftMenu.parse([
		{id: "segmentation", text: "Segmentation"}
	], "json");
	
		segemationLayout = layout.cells("c").attachLayout("4C");
		segemationLayout.cells("a").setText("Transverse");
		segemationLayout.cells("a").setWidth(layout.cells("c").getWidth()*0.7);

		segemationLayout.cells("b").setText("3D View");
		segemationLayout.cells("c").setText("Sagittal");
		segemationLayout.cells("d").setText("Coronal");


			
		var leftConorStatusBar = layout.cells("d").attachStatusBar({
			// status bar config here, optional
			text:   "window.width:" + $(window).width() + "window.height="+$(window).height(),   // status bar text
			height: 30                  // custom height
		});
 
 
		// get status bar text
			
		var scObj = segemationLayout.cells("a").attachStatusBar({
			// status bar config here, optional
			text:   "some text here",   // status bar text
			height: 35                  // custom height
		});
 
		// set status bar text
		scObj.setText("status on the transverse screen");
 
		//Append the Canvas inside the transverse 
			
		var transverseView = document.getElementById("transverse");
		segemationLayout.cells("a").attachObject(transverseView);
		segemationLayout.cells("a").hideHeader();

		var threeD = document.getElementById("threeD");
		segemationLayout.cells("b").attachObject(threeD);
		segemationLayout.cells("b").hideHeader();
		
		var sagittalView = document.getElementById("sagittal");
		segemationLayout.cells("c").attachObject(sagittalView);
		segemationLayout.cells("c").hideHeader();
		
			
		var coronalView = document.getElementById("coronal");
		segemationLayout.cells("d").attachObject(coronalView);
		segemationLayout.cells("d").hideHeader();

/*		

        menu = layout.attachMenu();                                                //initializes dhtmlxMenu
            menu.setIconsPath("icons/");                                               //sets the path to custom icons
            menu.loadStruct("data/menu.xml");                                             //loads items from the "data/menu.xml" file to the menu


            toolbar =  layout.cells("d").attachToolbar();                                          //initializes dhtmlxToolbar
            toolbar.setIconsPath("icons/");                                            //sets the path to custom images
            toolbar.loadStruct("data/toolbar.xml");                                       //loads items from the "data/toolbar.xml" file to the toolbar

           toolbar.attachEvent("onclick",function(id){                                //attaches a handler function to the "onclick" event
                if(id=="newContact"){                                                  //'newContact' is the id of the button in the toolbar
                    var rowId=contactsGrid.uid();                                      //generates an unique id
                    var pos = contactsGrid.getRowsNum();                               //gets the number of rows in the grid
                    contactsGrid.addRow(rowId,["New contact","",""],pos);              //adds a new row to the grid. The 'addRow()' method takes 3 parameters: the row id (must be unique), the initial values of the row, the  position where the new must be inserted
                };
                if(id=="delContact"){                                                  //'delContact' is the id of the button in the toolbar
                    var rowId = contactsGrid.getSelectedRowId();                       //gets the id of the currently selected row
                    var rowIndex = contactsGrid.getRowIndex(rowId);                    //gets the index of the row with the specified id

                    if(rowId!=null){
                        contactsGrid.deleteRow(rowId);                                 //deletes the currently selected row
                        if(rowIndex!=(contactsGrid.getRowsNum()-1)){                   //checks whether  the currently selected row is NOT last in the grid
                            contactsGrid.selectRow(rowIndex+1,true);                   //if the currently selected row isn't last - moves selection to the next row
                        } else{                                                        //otherwise, moves selection to the previous row
                            contactsGrid.selectRow(rowIndex-1,true)
                        }
                    }
                }
            });
*/

			//ribbon = layout.cells("d").attachRibbon();
            //ribbon.setIconsPath("icons/");                                               //sets the path to custom icons
            //ribbon.loadStruct("data/ribbon.xml");      



            contactsGrid = layout.cells("a").attachGrid();                             //initializes dhtmlxGrid
            contactsGrid.setHeader("Description, Value");                            //sets the header labels
            contactsGrid.setColumnIds("fname,lname");                            //sets the column ids
            //contactsGrid.setInitWidths("200,*");                                 //sets the initial widths of columns
            contactsGrid.setColAlign("left,left");                                //sets the horizontal alignment
            contactsGrid.setColTypes("ro,ro");                                      //sets the types of columns
            contactsGrid.setColSorting("str,str");                                 //sets the sorting types of columns
            //contactsGrid.attachHeader("#text_filter,#text_filter");       //sets the filters for columns
            contactsGrid.init();                                                       //renders  dhtmlxGrid on the page


 //contactsGrid.load("data/contacts.php");                                    //loads data from the "data/contacts.php" file to the grid
 
 /*
 The GridTree is supported inside the PRO edition
 			myTreeGrid = layout.cells("b").attachGrid();                             //initializes dhtmlxGrid
 
 			//myTreeGrid.setImagePath("../../../codebase/imgs/");
			myTreeGrid.setHeader("Tree,Plain Text,Text,Check 1,Check 2");
			myTreeGrid.setInitWidths("150,100,100,100,100");
			myTreeGrid.setColAlign("left,left,left,center,center");
			myTreeGrid.setColTypes("tree,ed,txt,ch,ch");
			myTreeGrid.setColSorting("str,str,str,na,str");
			myTreeGrid.init();
			//myTreeGrid.setSkin("dhx_skyblue");
			//myTreeGrid.load("data/structsgridtree.json", "json");
			
*/			

//            contactForm = layout.cells("b").attachForm();                              //initializes dhtmlxForm
//            contactForm.loadStruct("data/form.xml");                                   //loads controls, specified in the "data/form.xml" file to the form
//            contactForm.bind(contactsGrid);                                            //binds the form to the grid

/*            var dpg = new dataProcessor("data/contacts.php");                          //inits dataProcessor
            dpg.init(contactsGrid);                                                    //associates the dataProcessor instance with the grid

            dpg.attachEvent("onAfterUpdate", function(sid, action, tid, tag){
                if (action == "inserted"){
                    contactsGrid.selectRowById(tid);                                   //selects a row
                    contactForm.setFocusOnFirstActive();                               //sets focus to the 1st form's input
                }
            })

            contactForm.attachEvent("onButtonClick", function(id){                     //attaches a handler function to the "onButtonClick" event
                contactForm.save();                                                    //sends the values of the updated row to the server
            });

 */

/* 
 			myGrid = new dhtmlXGridObject('gridbox');
			myGrid.setHeader("Description,Value");
			myGrid.setInitWidths("150,*");
			myGrid.setColAlign("left,left");
			myGrid.setColSorting("server,na");
			myGrid.setColTypes("ro,ro");

			myGrid.load("data/patientinfo.json", "json");

			myTreeGrid = new dhtmlXGridObject('gridtbox');
			myTreeGrid.setImagePath("../../../codebase/imgs/");
			myTreeGrid.setHeader("Tree,Plain Text,Long Text,Color,Checkbox");
			myTreeGrid.setInitWidths("150,100,100,100,100");
			myTreeGrid.setColAlign("left,left,left,center,center");
			myTreeGrid.setColTypes("tree,ed,txt,ch,ch");
			myTreeGrid.setColSorting("str,str,str,na,str");
			myTreeGrid.init();
			myTreeGrid.setSkin("dhx_skyblue");
			myTreeGrid.load("../common/data.json", "json");
			
	*/					
			
			//call the prepare images

//    		var canvasTC = this.__canvasTC = new fabric.Canvas('TC');
//			var canvasSC = this.__canvasSC = new fabric.Canvas('SC');
//			var canvasCC = this.__canvasCC = new fabric.Canvas('CC');
  
  
   
    		var canvasTC = this.__canvasTC = new fabric.CanvasWithViewport("TC", {isDrawingMode: true});
			var canvasSC = this.__canvasSC = new fabric.CanvasWithViewport("SC", {isDrawingMode: false});
			var canvasCC = this.__canvasCC = new fabric.CanvasWithViewport("CC", {isDrawingMode: false});
  
  
    		
    		fabric.Object.prototype.transparentCorners = false;
    		canvasTC.hoverCursor = 'arrow';
			//canvasSC.hoverCursor = 'arrow';
			//canvasCC.hoverCursor = 'arrow';
			
  		function adjustmentAllViews() {
  		
  		    canvasTC.clear();
  		    
  		    canvasSC.clear();
  		    
			canvasCC.clear();
  		    
  		
        	canvasTC.setWidth($('#transverse').width());
        	canvasTC.setHeight($('#transverse').height());
        	canvasTC.calcOffset();
        	canvasTC.renderAll();
        	
        	
        	canvasSC.setWidth($('#sagittal').width());
	        canvasSC.setHeight($('#sagittal').height());
	        canvasSC.calcOffset();
	        canvasSC.renderAll();
        	
        	canvasCC.setWidth($('#coronal').width());
	        canvasCC.setHeight($('#coronal').height());
	        canvasCC.calcOffset();
	        canvasCC.renderAll();
        	
    	}			

        var DICOM_ROOT = "DICOM_Images/";
        var PatientID = "PID0001";
        var StudyID = "2_16_840_1_113669_632_20_1211_10000330985";
        var SeriesID = "1_2_840_113704_1_111_4848_1161868385_1";
		
		var transViewPos  = {
			x: rows/2-1,   //255, the default X is used for  locate the first slice of sagittal 
			y: columns/2-1, //255, the default Y is used for locate the first slice of coronal for display purpose 
			frame: 0
		}
		
		var sagittalViewPos = viewPosTransform("T", "S", transViewPos);
		var coronalViewPos = viewPosTransform("T", "S", transViewPos);
/*
		canvasTC.setWidth($('#transverse').width());
	    canvasTC.setHeight($('#transverse').width());
		fabric.Image.fromURL(dicom_t_image, function(oImg) {
  			    canvasTC.add(oImg.set({ left: 0, top: 0, angle: 0 }).scale(1));
		});
	    canvasTC.calcOffset();
	    canvasTC.renderAll();
*/

		function getObjectWithName(canvas, name)
		{
		    var objects = canvas.getObjects();
            for (var i in objects) {
                
                if ( objects[i].name === name) 
                {
                	return objects[i];
                }
            }
		}
		
		//calc the scale according the height 
		
		function drawTransverse(transViewPos)
		{
			//TODO get the current image sliced that should be displayed. 
			var dicom_t_image = DICOM_ROOT + PatientID + "/" + StudyID  + "/" + SeriesID + "/T/T_" + SeriesID + "_"+ transViewPos.frame + ".png";
			fabric.Image.fromURL(dicom_t_image, function(img) {
		
				var containerWidth = $('#transverse').width();
				var containerHeight = $('#transverse').height(); 
				
				var scaleValue = containerHeight/img.height; 
				
	            img.scale(scaleValue).set({
	                left: (containerWidth - img.width * scaleValue)/2,
	                top: 0,
	                hasControls: false,
	                hasBorders: false,
	                lockMovementX: false,
	                lockMovementY: false
	            	});
	            canvasTC.add(img).setActiveObject(img);
	        	});
        	canvasTC.clear();
		
		}
		
		function drawSagittal(sagittalViewPos) 
		{
			var dicom_s_image = DICOM_ROOT + PatientID + "/" + StudyID  + "/" + SeriesID + "/S/S_"+SeriesID+"_"+sagittalViewPos.frame+".png";
		
	        fabric.Image.fromURL(dicom_s_image, function(img) {
	  			var containerWidth = $('#sagittal').width();
				var containerHeight = $('#sagittal').height(); 
				
				var physicalHWMulti = pixelSpacingSlice/pixelSpacingX; 
	
				var physicalImageWidth = img.width; //for example: 512
				var physicalImageHeight = img.height * physicalHWMulti; //for example: 393 * 1.5 = 593 
				
				var basicScale; 
				if  (physicalImageHeight>physicalImageWidth)
				{
					basicScale = containerHeight/physicalImageHeight;
				}	
				else 
				{
					basicScale = containerWidth/physicalImageWidth;
				}		

	          
	          //img.filters.push(new fabric.Image.filters.RemoveWhite({ threshold:10 ,distance: 100 }));
	    	  //img.applyFilters(canvasSC.renderAll.bind(canvasSC));
	     			  
	        	canvasSC.setBackgroundImage(img, canvasSC.renderAll.bind(canvasSC), {
	    			scaleX: basicScale,
	    			scaleY: basicScale * physicalHWMulti,
	    			//width:  physicalImageWidth*(physicalImageHeight/canvasCC.height),
	  				//height: physicalImageHeight,
					left: (containerWidth - (img.width * basicScale))/2,
	                top: 0,    	
	       		}); 

			$('#s_bottomright_frame').text("Frame: " + sagittalViewPos.frame);
	       		
			//add lines onthe sagittal canvas view. x1, y1, x2, y2
			var x1 = 0; // sagittalViewPos.x is the actual pixel, need to convert the scaled image container's x
			var x2 = containerWidth;
			var y1 = y2 = sagittalViewPos.y * basicScale * physicalHWMulti;  
		
		   	console.log("draw sagittal hor line (" + x1 + ", " + y2 + ") (" + x2 + ", " + y2 + ")");
		
		
		    var horLineSC = new fabric.Line([x1, y1, x2, y2], {
		        //left: 0,
		        //top: 100,
		        stroke: 'blue',
		        lockMovementX: true,
		        lockMovementY: false,
		        hasControls:false
		    });
		    horLineSC.set("name", "h");
		    
		    horLineSC.on('moving', function(e) {
				  console.log('moving a horLineSC');
				  
				  var point = this.getCenterPoint();
				  if (point.y<0)
				  {
				  	  horLineSC.set("y1", 0);
				  	  horLineSC.set("y2", 0);
				  }
				  else if (point.y>canvasSC.getHeight())
				  {
				  	  horLineSC.set("y1", canvasSC.getHeight());
				  	  horLineSC.set("y2", canvasSC.getHeight());
				  }
				  
				  point = this.getCenterPoint();
				  leftConorStatusBar.setText("sagittal horLineCC : moving " + point.x + "," + point.y);
				  
				  
				  sagittalViewPos.y = Math.round(point.y / (basicScale *physicalHWMulti )) - 1; //should be converted to the 512 pixel based value

				//calculate the viewPost and tranform it to the saggital and 
				//get the current frame: x is moding in this direction 
				transverseViewPos = viewPosTransform("S", "T", sagittalViewPos); 
												  
				//adjust the sagittal's horline
				var horLineCC = getObjectWithName(canvasCC, "h");
				
				var y_cc =  (transverseViewPos.frame * canvasCC.height) / totalFrames; 
						
				horLineCC.set("y1", y_cc);
				horLineCC.set("y2", y_cc);
				horLineCC.setCoords();
				canvasCC.renderAll();
				 

			    drawTransverse(transverseViewPos);
			  
				$('#bottomright_frame').text("Frame: " + transverseViewPos.frame);
				
				//use the transverseViewPos.frame to locate the coronal view's line				 
				 
			});
		    
		    canvasSC.add(horLineSC);
		
		    //horLineSC.bringForward();
		    //canvasSC.renderAll();
		    
			x1 = x2 = (containerWidth - (img.width * basicScale))/2 + sagittalViewPos.x * basicScale ; // sagittalViewPos.x is the actual pixel, need to convert the scaled image container's x
			y1 = 0;  
			y2 = containerHeight;  
		 
		    var vetLineSC = new fabric.Line([x1, y1, x2, y2], {
		        //left: 0,
		        //top: 100,
		        stroke: 'green',
		        lockMovementX: false,
		        lockMovementY: true,
		        hasControls: false
		    });
			vetLineSC.set("name", "v");		    
			
			
			    //capture the vertical line movement 
			    vetLineSC.on('moving', function(e) {
				  console.log('moving a vetLineSC');
				  
				  var point = this.getCenterPoint();
				  if (point.x<(containerWidth - (img.width * basicScale))/2)
				  {
				  	  vetLineSC.set("x1", (containerWidth - (img.width * basicScale))/2);
				  	  vetLineSC.set("x2", (containerWidth - (img.width * basicScale))/2);
				  }else if (point.x> ( (containerWidth - (img.width * basicScale))/2 + img.width*basicScale) )
				  {
				  	  vetLineSC.set("x1", (containerWidth - (img.width * basicScale))/2 + img.width*basicScale);
				  	  vetLineSC.set("x2", (containerWidth - (img.width * basicScale))/2 + img.width*basicScale);
				  }
				  
				  point = this.getCenterPoint();
				  
				  //redraw the sagittal view here:
				  
				  leftConorStatusBar.setText("vetLineSC : moving " + point.x + "," + point.y);
				  
				  //calculate the viewPost and tranform it to the saggital and 
				  //get the current frame: x is moding in this direction
				   
				 sagittalViewPos.x = Math.round((point.x - (containerWidth - (img.width * basicScale))/2) / basicScale) - 1; //should be converted to the 512 pixel based value
				  
				  //redraw the sagittal view here:
				  
		   		console.log("sagittalViewPos x,y (" + sagittalViewPos.x + ", " + sagittalViewPos.y + ")" );
				  
				//calculate the viewPost and tranform it to the saggital and 
				//get the current frame: x is moding in this direction 
				  

				coronalViewPos = viewPosTransform("S", "C", sagittalViewPos); 
				    
				displayCoronalSlice(coronalViewPos);
				  
				});	 		    
		    canvasSC.add(vetLineSC);
		    
		    //canvasCC.moveTo(horLine, -1);
		
		    //vetLineSC.bringForward();
		    
	       		   
			});
		}//end of function
		
		function displaySagittalSlice(viewPos)
		{
			var dicom_s_image = DICOM_ROOT + PatientID + "/" + StudyID  + "/" + SeriesID + "/S/S_"+SeriesID+"_"+sagittalViewPos.frame+".png";
		
	        fabric.Image.fromURL(dicom_s_image, function(img) {
	  			var containerWidth = $('#sagittal').width();
				var containerHeight = $('#sagittal').height(); 
				
				var physicalHWMulti = pixelSpacingSlice/pixelSpacingX; 
	
				var physicalImageWidth = img.width; //for example: 512
				var physicalImageHeight = img.height * physicalHWMulti; //for example: 394 * 1.5 = 593 
				
				var basicScale; 
				if  (physicalImageHeight>physicalImageWidth)
				{
					basicScale = containerHeight/physicalImageHeight;
				}	
				else 
				{
					basicScale = containerWidth/physicalImageWidth;
				}		

	          
	          //img.filters.push(new fabric.Image.filters.RemoveWhite({ threshold:10 ,distance: 100 }));
	    	  //img.applyFilters(canvasSC.renderAll.bind(canvasSC));
	     			  
	        	canvasSC.setBackgroundImage(img, canvasSC.renderAll.bind(canvasSC), {
	    			scaleX: basicScale,
	    			scaleY: basicScale*physicalHWMulti,
	    			//width:  physicalImageWidth*(physicalImageHeight/canvasCC.height),
	  				//height: physicalImageHeight,
					left: (containerWidth - (img.width * basicScale))/2,
	                top: 0,    	
	       		}); 
			});
		
   	        $('#s_bottomright_frame').text("Frame: " + viewPos.frame);
		   canvasSC.renderAll();				
		}
		
		
		function drawCoronal(coronalViewPos)
		{
			var dicom_c_image = DICOM_ROOT + PatientID + "/" + StudyID  + "/" + SeriesID + "/C/C_" + SeriesID + "_"+coronalViewPos.frame+".png";
		
      		fabric.Image.fromURL(dicom_c_image, function(img) {
	  			var containerWidth = $('#coronal').width();
				var containerHeight = $('#coronal').height(); 
				
				var physicalHWMulti = pixelSpacingSlice/pixelSpacingX; 
	
				var physicalImageWidth = img.width; //for example: 512
				var physicalImageHeight = img.height * physicalHWMulti; //for example: 394 * 1.5 = 593 
				
				var basicScale; 
				if  (physicalImageHeight>physicalImageWidth)
				{
					basicScale = containerHeight/physicalImageHeight;
				}	
				else 
				{
					basicScale = containerWidth/physicalImageWidth;
				}		
				
				//var scaleValue = containerHeight/img.height;
				/* 
	            img.set({
	                left: 0, //(containerWidth - img.width * scaleXValue)/2,
	                top: 0,
	                hasControls: false,
	                hasBorders: false,
	                scaleX: 0.5, 
	                scaleY: 0.8,                 
	                lockMovementX: true,
	                lockMovementY: true
	            });
	            */
	            //canvasCC.add(img).setActiveObject(img);

				//canvasCC.dispose();//clear the lines beofre redraw 
            
		        canvasCC.setBackgroundImage(img, canvasCC.renderAll.bind(canvasCC), {
		    			scaleX: basicScale,
		    			scaleY: basicScale *physicalHWMulti,
		    			//width:  physicalImageWidth*(physicalImageHeight/canvasCC.height),
		  				//height: physicalImageHeight,
						left: (containerWidth - (img.width * basicScale))/2,
						top: 0,
						originX: 'left',
						originY: 'top'    			
		       });
		       
				$('#c_bottomright_frame').text("Frame: " + coronalViewPos.frame);
		       
			//add lines onthe coronal canvas view. 
			//add lines onthe sagittal canvas view. x1, y1, x2, y2
			var x1 = 0; // sagittalViewPos.x is the actual pixel, need to convert the scaled image container's x
			var x2 = containerWidth;
			var y1 = y2 = sagittalViewPos.y * basicScale * physicalHWMulti;  
		
			    var horLineCC = new fabric.Line([x1, y1, x2, y2], {
			        //left: 0,
			        //top: 100,
			        stroke: 'blue',
			        lockMovementX: true,
			        lockMovementY: false,
			        hasControls: false
			    })
			    
			    horLineCC.set("name", "h"); //give a name to the line so that the line can be retrieved easily later
			     
			    canvasCC.add(horLineCC);
			    //canvasCC.moveTo(horLine, -1);
			
			    //horLineCC.bringForward();
			    //horLineCC.centerV(); 
			    //horLineCC.setCoords();
			    
			
			    //capture the vertical line movement
 
			    horLineCC.on('moving', function(e) {
			    

				  console.log('moving a horLineCC');
				  
				  var point = this.getCenterPoint();
				  if (point.y<0)
				  {
				  	  horLineCC.set("y1", 0);
				  	  horLineCC.set("y2", 0);
				  }else if (point.y>canvasCC.getHeight())
				  {
				  	  horLineCC.set("y1", canvasCC.getHeight());
				  	  horLineCC.set("y2", canvasCC.getHeight());
				  }
				  
				  point = this.getCenterPoint();
				  leftConorStatusBar.setText("horLineCC : moving " + point.x + "," + point.y);
				  
				  //calculate the viewPost and tranform it to the saggital and 
				  //get the current frame: x is moding in this direction 
				  
				  coronalViewPos.y = Math.round(point.y / (basicScale  *physicalHWMulti )) - 1; //should be converted to the 512 pixel based value
				  
				  //redraw the sagittal view here:
				  
		   		console.log("coronalViewPos x,y (" + coronalViewPos.x + ", " + coronalViewPos.y + ")" );
				  
				//adjust the sagittal's horline
				var horLineSC = getObjectWithName(canvasSC, "h");
				

				//calculate the viewPost and tranform it to the saggital and 
				//get the current frame: x is moding in this direction 
				transverseViewPos = viewPosTransform("C", "T", coronalViewPos); 

				//get the y position from the frame value of transverseViewPos, and then convert it the "y" value on the canvasSC
				var y_sc =  transverseViewPos.frame * canvasSC.height / totalFrames; 
				 
				horLineSC.set("y1", y_sc);
				horLineSC.set("y2", y_sc);
				horLineSC.setCoords();
				canvasSC.renderAll();
			        drawTransverse(transverseViewPos);
				    $('#bottomright_frame').text("Frame: " + transverseViewPos.frame);
				  
				});
			
			     
			
			x1 = x2 = (containerWidth - (img.width * basicScale))/2 + coronalViewPos.x * basicScale ; // sagittalViewPos.x is the actual pixel, need to convert the scaled image container's x
			y1 = 0;  
			y2 = containerHeight;  
			
			    var vetLineCC = new fabric.Line([x1, y1, x2, y2], {
			        //left: 0,
			        //top: 100,
			        stroke: 'green',
			        lockMovementX: false,
			        lockMovementY: true,
			        hasControls: false
			    })
			    vetLineCC.set("name", "v"); //give a name to the line so that the line can be retrieved easily later
			    //capture the vertical line movement 
			    vetLineCC.on('moving', function(e) {
				  console.log('moving a vetLineCC');
				  
				  var point = this.getCenterPoint();
				  if (point.x<(containerWidth - (img.width * basicScale))/2)
				  {
				  	  vetLineCC.set("x1", (containerWidth - (img.width * basicScale))/2);
				  	  vetLineCC.set("x2", (containerWidth - (img.width * basicScale))/2);
				  }else if (point.x> ( (containerWidth - (img.width * basicScale))/2 + img.width*basicScale) )
				  {
				  	  vetLineCC.set("x1", (containerWidth - (img.width * basicScale))/2 + img.width*basicScale);
				  	  vetLineCC.set("x2", (containerWidth - (img.width * basicScale))/2 + img.width*basicScale);
				  }
				  
				  point = this.getCenterPoint();
				  
				  //redraw the sagittal view here:
				  
				  leftConorStatusBar.setText("vetLineCC : moving " + point.x + "," + point.y);
				  
				  //calculate the viewPost and tranform it to the saggital and 
				  //get the current frame: x is moding in this direction 
				  
				  coronalViewPos.x = Math.round((point.x - (containerWidth - (img.width * basicScale))/2) / basicScale) - 1; //should be converted to the 512 pixel based value
				  
				  //redraw the sagittal view here:
				  
		   		  console.log("coronalViewPos x,y (" + coronalViewPos.x + ", " + coronalViewPos.y + ")" );
				  
				  //calculate the viewPost and tranform it to the saggital and 
				  //get the current frame: x is moding in this direction 
				  
 				   sagittalViewPos = viewPosTransform("C", "S", coronalViewPos); 
				    
				  displaySagittalSlice(sagittalViewPos);
				});	 
		
			    canvasCC.add(vetLineCC);
			    //canvasCC.moveTo(horLine, -1);
			
			    //vetLineCC.bringForward();       
			});
	      //  canvasCC.clear();
		 //   canvasCC.renderAll();
		}
		
		function displayCoronalSlice(viewPos)
		{
			var dicom_c_image = DICOM_ROOT + PatientID + "/" + StudyID  + "/" + SeriesID + "/C/C_" + SeriesID + "_"+viewPos.frame+".png";
		
      		fabric.Image.fromURL(dicom_c_image, function(img) {
	  			var containerWidth = $('#coronal').width();
				var containerHeight = $('#coronal').height(); 
				
				var physicalHWMulti = pixelSpacingSlice/pixelSpacingX; 
	
				var physicalImageWidth = img.width; //for example: 512
				var physicalImageHeight = img.height * physicalHWMulti; //for example: 394 * 1.5 = 593 
				
				var basicScale; 
				if  (physicalImageHeight>physicalImageWidth)
				{
					basicScale = containerHeight/physicalImageHeight;
				}	
				else 
				{
					basicScale = containerWidth/physicalImageWidth;
				}		
		        canvasCC.setBackgroundImage(img, canvasCC.renderAll.bind(canvasCC), {
		    			scaleX: basicScale,
		    			scaleY: basicScale * physicalHWMulti,
		    			//width:  physicalImageWidth*(physicalImageHeight/canvasCC.height),
		  				//height: physicalImageHeight,
						left: (containerWidth - (img.width * basicScale))/2,
						top: 0,
						originX: 'left',
						originY: 'top'    			
		       });
		       $('#c_bottomright_frame').text("Frame: " + viewPos.frame);
		   });		
		   
		   canvasCC.renderAll();		   
		}

		drawTransverse(transViewPos);
        drawSagittal(sagittalViewPos);
        drawCoronal(coronalViewPos);
        adjustmentAllViews();		

/*		
		function newLine(x1, y1, x2, y2, color) {
    var line = new fabric.Line(
    [x1, y1, x2, y2], {
        stroke: color,
        strokeWidth: 15,
        selectable: true
    });
    return (line);
};

	var line1 = newLine(50, 100, 233, 100, "red");
	var line2 = newLine(160, 10, 160, 150, "green");
	canvasCC.add(line1, line2);
*/




    $('#coronal').on('mousewheel DOMMouseScroll', function (e) {
	  // Firefox e.originalEvent.detail > 0 scroll back, < 0 scroll forward
		// chrome/safari e.originalEvent.wheelDelta < 0 scroll back, > 0 scroll forward
		var c = canvasCC;
		if (e.originalEvent.wheelDelta < 0 || e.originalEvent.detail > 0) {
			//scroll forward
			//get current slice and make sure the slice +1, if the slice > 0 the bigges the frame number, for example 511, then make is always be 511
					
		} else {
	        //scroll back
	        //get the current slice and make sure the slice -1, if the slice <0, then make is always be 0 

		}
		//prevent page fom scrolling
		return false;
	});
			

     /*
		canvasCC.on('object:moving', function(options) {
	//	e.target.setFill('red');
	//	canvasCC.renderAll();
    
		leftConorStatusBar.setText("canvasCC :mouse:over" + options.e.clientX + "," + options.e.clientY);
  });
  */

			canvasTC.on('mouse:move', function (options) {
	    	    var p = canvasTC.getPointer(options.e);
	        	x = p.x;
	        	y = p.y;
	        	console.log(p.x + " " + p.y + " " + canvasTC._offset.left + " " + canvasTC._offset.top);
	        	
	        	scObj.setText(p.x + " " + p.y + " " + canvasTC._offset.left + " " + canvasTC._offset.top);
	        	
		    });	
		    	
			segemationLayout.attachEvent("onResizeFinish", function(){
					
				// set status bar text
				leftConorStatusBar.setText("onResizeFinish : onResizeFinish");
				
				drawTransverse(transViewPos);
	        	drawSagittal(sagittalViewPos);
    	    	drawCoronal(coronalViewPos);
        		adjustmentAllViews();		
			});
			
			segemationLayout.attachEvent("onPanelResizeFinish", function(names){
					leftConorStatusBar.setText("onPanelResizeFinish:" + $(window).width() + "window.height="+$(window).height());

				drawTransverse(transViewPos);
	        	drawSagittal(sagittalViewPos);
    	    	drawCoronal(coronalViewPos);
        		adjustmentAllViews();
        		
			});
			
			
			segemationLayout.attachEvent("onCollapse", function(name){

				drawTransverse(transViewPos);
		        drawSagittal(sagittalViewPos);
	    	    drawCoronal(coronalViewPos);
	        	adjustmentAllViews();
			});

			segemationLayout.attachEvent("onExpand", function(name){
				drawTransverse(transViewPos);
		        drawSagittal(sagittalViewPos);
	    	    drawCoronal(coronalViewPos);
	        	adjustmentAllViews();
			});
			
			
			$('#transverse').on('mousewheel DOMMouseScroll', function (e) {
				// Firefox e.originalEvent.detail > 0 scroll back, < 0 scroll forward
				// chrome/safari e.originalEvent.wheelDelta < 0 scroll back, > 0 scroll forward
				var c = canvasTC;
				if (e.originalEvent.wheelDelta < 0 || e.originalEvent.detail > 0) {
					c.setZoom(c.viewport.zoom*1.1);
					
				} else {
			      c.setZoom(c.viewport.zoom*0.9);

				}
				//prevent page fom scrolling
				return false;
			});
/*			
 function getPixelData( image ) {

    var canvas = document.createElement( 'canvas' );
    canvas.width = image.width;
    canvas.height = image.height;

    var context = canvas.getContext( '2d' );
    context.drawImage( image, 0, 0 );

    return context.getImageData( 0, 0, image.width, image.height );

}
 
 
 
    function getImageData(imageObj) {

        var width = 256;
        var height = 256;

        var image = {
            imageId: imageObj.src,
            minPixelValue : 0,
            maxPixelValue : 257,
            slope: 1.0,
            intercept: 0,
            windowCenter : 127,
            windowWidth : 256,
            render: cornerstone.renderGrayscaleImage,
            getPixelData: getPixelData(imageObj),
            rows: height,
            columns: width,
            height: height,
            width: width,
            color: false,
            columnPixelSpacing: .8984375,
            rowPixelSpacing: .8984375,
            sizeInBytes: width * height * 2
        };

        var deferred = $.Deferred();
        deferred.resolve(image);
        return deferred;
    }
		*/
		/*	
		function prepareImages(){
		

			//load the image and display it
		
	      var imageObj = new Image();
	      imageObj.onload = function() {

	        
	        var image = getImageData(this);
	        cornerstone.displayImage(element, image);
	        
	      };
	      imageObj.src = dicom_t_image;


			// image enable the dicomImage element
			var element = $('#transverse').get(0);
			cornerstone.enable(element);

			var imageId = 'example://1';

			cornerstone.loadImage(imageId).then(function(image) {
				cornerstone.displayImage(element, image);
			});

			// Add event handlers to zoom the image in and out
			$('#zoomIn').click(function (e) {
				var viewport = cornerstone.getViewport(element);
				viewport.scale += 0.25;
				cornerstone.setViewport(element, viewport);
			});
			$('#zoomOut').click(function (e) {
				var viewport = cornerstone.getViewport(element);
				viewport.scale -= 0.25;
				cornerstone.setViewport(element, viewport);
			});
			$('#reset').click(function (e) {
				cornerstone.reset(element);
			});

			// add event handlers to pan image on mouse move
			$('#transverse').mousedown(function (e) {
				var lastX = e.pageX;
				var lastY = e.pageY;


				$(document).mousemove(function (e) {
					var deltaX = e.pageX - lastX,
							deltaY = e.pageY - lastY;
					lastX = e.pageX;
					lastY = e.pageY;

					var viewport = cornerstone.getViewport(element);
					viewport.translation.x += (deltaX / viewport.scale);
					viewport.translation.y += (deltaY / viewport.scale);
					cornerstone.setViewport(element, viewport);
				});

				$(document).mouseup(function (e) {
					$(document).unbind('mousemove');
					$(document).unbind('mouseup');
				});
			});

			$('#transverse').on('mousewheel DOMMouseScroll', function (e) {
				// Firefox e.originalEvent.detail > 0 scroll back, < 0 scroll forward
				// chrome/safari e.originalEvent.wheelDelta < 0 scroll back, > 0 scroll forward
				if (e.originalEvent.wheelDelta < 0 || e.originalEvent.detail > 0) {
					var viewport = cornerstone.getViewport(element);
					viewport.scale -= 0.25;
					cornerstone.setViewport(element, viewport);
				} else {
					var viewport = cornerstone.getViewport(element);
					viewport.scale += 0.25;
					cornerstone.setViewport(element, viewport);
				}
				//prevent page fom scrolling
				return false;
			});
		}

		*/					
			
						
        })
 
 
    </script>
        
</@layout.header>

<@layout.body>
<div id="patientGridbox" style="width:100% display:none;"></div>
<div id="structgridbox" style="width:100% display:none;"></div>

<div id="transverse" class="segementation_window">
<canvas id = "TC" ></canvas>
        <div id="topleft" style="position: absolute;top:0px; left:0px;color: white;">
            Patient Name
        </div>
        <div id="topright" style="position: absolute;top:0px; right:0px;color: white;">
            Hospital
        </div>
        <div id="bottomright" style="position: absolute;bottom:0px; right:0px;color: white;">
	        <div id="bottomright_frame" style="color: white;">
        	    Frame: 0
    	    </div>

        </div>
        
        <div id="bottomleft" style="position: absolute;bottom:0px; left:0px;color: white;">
            WW/WC:
        </div>
</div>
<div id="sagittal" class="segementation_window">
<canvas id = "SC">
</canvas>
        <div id="s_bottomright" style="position: absolute;bottom:0px; right:0px;color: white;">
	        <div id="s_bottomright_frame" style="color: white;">
        	    Frame: 
    	    </div>
        </div>
</div>
<div id="coronal" class="segementation_window">
<canvas id = "CC"></canvas>
        <div id="c_bottomright" style="position: absolute;bottom:0px; right:0px;color: white;">
	        <div id="c_bottomright_frame" style="color: white;">
        	    Frame: 
    	    </div>
        </div>
</div>
<div id="threeD" class="segementation_window">
<canvas id = "3DC"></canvas>
</div>
   
</@layout.body>
 
 <@layout.footer>

</@layout.footer>
 
