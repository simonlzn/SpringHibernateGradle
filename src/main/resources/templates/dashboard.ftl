<#import "/spring.ftl" as spring/>
<#import "include/layout.ftl" as layout> 

<@layout.header "Dashboard", "dashboard page">

    <!-- dhtmlx.css contains styles definitions for all use components -->
    <link rel="STYLESHEET" href="js/dhtmlx/dhtmlx.css" type="text/css" media="screen">
    
    <!-- dhtmlx.js contains all necessary dhtmlx library javascript code -->
    <script src="js/dhtmlx/dhtmlx.js"></script>
    
   <script type="text/javascript">
        var layout,menu,toolbar,contactsGrid,contactForm;
		var seriesObject; //the series images that is under the contouring. It includes the control functions to display the proper informations inside the view and which pages should be displayed at the current
        //dhtmlx.image_path = "codebase/imgs/";
        
        var myGrid;
		var timeoutHnd;
		var flAuto = false;
		
		function setCounter(){
			var span = document.getElementById("recfound");
			span.style.color = "";
			span.innerHTML = myGrid.getRowsNum();
		}
		function showLoading(){
			var span = document.getElementById("recfound");
			if(!!myGrid.setColspan){
				span.innerHTML = "<i>available in Professional Edition of dhtmlxGrid</i>"
				return;
			}
			span.style.color = "red";
			span.innerHTML = "loading...";
		}
		function doSearch(ev){
			if(!flAuto) return;
			var elem = ev.target||ev.srcElement;
			if(timeoutHnd) clearTimeout(timeoutHnd);
			timeoutHnd = setTimeout(reloadGrid,500)
		}
		function reloadGrid(){
			var nm_mask = document.getElementById("search_nm").value
			var cd_mask = document.getElementById("search_cd").value
			myGrid.clearAll();
			myGrid.load("data/patientinfo.json", "json");
			if (window.a_direction) myGrid.setSortImgState(true,window.s_col,window.a_direction);
			showLoading()
		}
		function enableAutosubmit(state){
			flAuto = state;
			document.getElementById("submitButton").disabled = state
		}
		function customColumnSort(ind){
			if (ind==1) {
				alert("Table can't be sorted by this column.");
				if (window.s_col)
					myGrid.setSortImgState(true,window.s_col,window.a_direction);
				return false;
			}
			var a_state = myGrid.getSortingState();
			window.s_col=ind;
			window.a_direction = ((a_state[1] == "des")?"asc":"des");
            reloadGrid();
			return false;
		}

		function onButtonClick(menuitemId,type)
		{
			
			alert("menuitemId " + menuitemId + " is selected" + " type = " + type);
			
			//var data = myGrid.contextID.split("_"); //rowId_colInd
			//myGrid.setRowTextStyle(data[0],"color:"+menuitemId.split("_")[1]);
			if("patientInfo"==menuitemId)
			{
				window.open("patientDetail");
			}
			return true
		}
		
		function swithToSegmentation()
		{
			//
				window.open("segmentation");
		}
		
		
					        
        dhtmlxEvent(window,"load",function(){                                          //provides the script as a handler of the 'onload' HTML event
			layout = new dhtmlXLayoutObject(document.body,"2U");                       //initializes dhtmlxLayout

            layout.cells("a").setText("Patient Information");                                     //sets the text in the header of the 'grid' column
			layout.cells("a").hideHeader();
            layout.cells("a").setWidth($(window).width() * 0.8);
            
            layout.cells("a").appendObject("patientList");;
            
            
 			myGrid = new dhtmlXGridObject('gridbox');
			//myGrid.setImagePath("../../../codebase/imgs/");
			myGrid.setHeader("Patient ID,Patient Name,Tumor,P/H/C,Doctor, Planner, Registration Date, CF-SIM, Finish Contour, Finish Planning, Plan Check, Start TR, End TR, Note");
			myGrid.setInitWidths("250,250,*");
			myGrid.setColAlign("left,left,left");
			myGrid.setColSorting("server,na,server");
			myGrid.setColTypes("ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro,ro");
			//available in pro version only
			myGrid.attachEvent("onBeforeSorting",customColumnSort);
			myGrid.init();
			myGrid.enableSmartRendering(true);
			//available in Pro Edition only
			if (myGrid.setColspan) {
			   	myGrid.attachEvent("onXLE",setCounter);
			} else {
				//code below written to support standard edtiton
				//it written especially for current sample and may not work
				//in other cases, DON'T USE it if you have pro version
				myGrid.sortField_old=myGrid.sortField;
				myGrid.sortField=function(){
					myGrid.setColSorting("str,str,str");
					if (customColumnSort(arguments[0])) myGrid.sortField_old.apply(this,arguments);
				};
				myGrid.sortRows=function(col,type,order){}
			}
			myGrid.load("data/patientlist.json", "json");
			showLoading();

			//adding the context menu to the grid rows
			var myMenu = new dhtmlXMenuObject();
			//myMenu.setIconsPath("../common/images/");
			myMenu.renderAsContextMenu();
			myMenu.attachEvent("onClick",onButtonClick);
			myMenu.loadStruct("data/menu.xml");

			myGrid.enableContextMenu(myMenu);


		
			            
            layout.cells("b").setText("Status");                              //sets the text in the header of the 'form' column
            layout.cells("b").hideHeader();
			
			var sbObj = layout.cells("b").attachStatusBar({
				// status bar config here, optional
				text:   "some text here",   // status bar text
				height: 35                  // custom height
			});
 
			// set status bar text
			sbObj.setText("Status Text Listed Here");
	
			//Top menu and the right left tools
	        toolbar1 = layout.attachToolbar();                                          //initializes dhtmlxToolbar
            toolbar1.setIconsPath("icons/");                                            //sets the path to custom images
            toolbar1.loadStruct("data/toolbar.xml");                                       //loads items from the "data/toolbar.xml" file to the toolbar

	
		leftMenu = layout.cells("b").attachDataView({
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
		{id: "patientImport", text: "Upload DICOM"},
		{id: "segmentation", text: "Segmentation"}
	], "json");
	


//display the form 
var dhxWins;
var myForm, formData;

leftMenu.attachEvent("onItemClick", function (id, ev, html){
    // your code here
    sbObj.setText("icon " + id + " is clicked; " + layout.cells("b").getWidth());
    
    if("patientImport"===id)
    {
	    if (dhxWins==null)
	    {
			dhxWins = new dhtmlXWindows();
			dhxWins.attachViewportTo(layout.cells("b").cell);
				
			dhxWins.vp.style.border = "#a4bed4 1px solid";
			dhxWins.vp.style.borderRadius = "2px";

			w1 = layout.dhxWins.createWindow("w1",0, 200, layout.cells("b").getWidth(), 200);
			w1.centerOnScreen();
			w1.setModal(true);		
							
			//w1 = dhxWins.createWindow("w1", 0, 200, layout.cells("b").getWidth(), 200);
			//w1.button("close").disable();
			//w1.denyResize();
			//w1.keepInViewport(false);
	
			formData = [
					{type: "fieldset", label: "Upload DICOM Files", list:[
						{type: "upload", name: "myFiles", inputWidth: 330, url: "php/dhtmlxform_item_upload.php", _swfLogs: "enabled", swfPath: "uploader.swf", swfUrl: "php/dhtmlxform_item_upload.php"}
					]}
			];
	
			
			myForm = w1.attachForm(formData, true);
	
	

	    }
	    
	    
	    if(dhxWins.window("w1")==null)
	    {
			//w1 = dhxWins.createWindow("w1", 0, 200, layout.cells("b").getWidth(), 200);
			//w1.button("close").disable();
			//w1.denyResize();
			//w1.keepInViewport(false);
			
			w1 = layout.dhxWins.createWindow("w1",0, 200, layout.cells("b").getWidth(), 200);
			w1.setModal(true);
			w1.centerOnScreen();			
	
			formData = [
					{type: "fieldset", label: "Upload DICOM Files", list:[
						{type: "upload", name: "myFiles", inputWidth: 330, url: "php/dhtmlxform_item_upload.php", _swfLogs: "enabled", swfPath: "uploader.swf", swfUrl: "php/dhtmlxform_item_upload.php"}
					]}
			];
	
			
			myForm = w1.attachForm(formData, true);
	    	
	    	
	    }
	    else 
	    {
	    	if (dhxWins.window("w1").isHidden())
	    	{
	    		dhxWins.window("w1").show();
	    	}
	    }
    }
    else if ("segmentation"===id)
    {
    	//alert("segmentation is clicked");
    	//if(dhxWins!=null && dhxWins.window("w1")!=null)
    //	{
    //		dhxWins.window("w1").hide();
    //	}
    	
	   	console.log("myGrid.getSelectedRowId = " + myGrid.getSelectedRowId() + ", myGrid.getSelectedCellIndex() " + myGrid.getSelectedCellIndex() + ") ");

		if(myGrid.getSelectedRowId != null)
		{
	    	var patientId = myGrid.cells(myGrid.getSelectedRowId(), 0).getValue();
	    	
	    	//alert("patientId = " + patientId + " is selected"); 
	    	console.log("opening patientId = " + patientId );
	    	 
			swithToSegmentation();
    	}
    }
    
    
    return true;
});

		// check windows' positions if cell resized
		layout.attachEvent("onPanelResizeFinish", function(cells){
			for (var q=0; q<cells.length; q++) {
				if (cells[q] == "a") {
					// cell "a" was resized, check windows' positions (out-of-viewport)
					dhxWins.forEachWindow(function(win){
						win.adjustPosition();
					});
				}
			}
		});
			
})
					
    </script>
    

</@layout.header>

<@layout.body>
<div id="patientList" style="display:none;">
	<h1>Patient List</h1>
	<div>
		Patient ID: <input type="text" id="search_nm" onKeyDown="doSearch(arguments[0]||event)">
		Patient Name: <input type="text" id="search_cd" onKeyDown="doSearch(arguments[0]||event)">
		Turmor: <input type="text" id="search_cd" onKeyDown="doSearch(arguments[0]||event)">
		
		Patient Name: <input type="text" id="search_cd" onKeyDown="doSearch(arguments[0]||event)">
		
		<button onClick="reloadGrid()" id="submitButton" style="margin-left:30px;">Search</button>
		<input type="checkbox" id="autosearch" onClick="enableAutosubmit(this.checked)"> Enable Autosearch
	</div>
	<div id="gridbox" style="width:100%;height:300px;margin-top:20px;margin-bottom:10px;"></div>
	<div>Records found: <span id="recfound"></span></div>
	
	<div class="icon icon-add-001"></div>
	
	          <div class="icon icon-configuration"></div>
	
	
	
</div>	

<div id="winVP" style="text-align:center;display: none;">
	<div id="myForm"></div>
</div>

  
</@layout.body>

 <@layout.footer>



</@layout.footer>
