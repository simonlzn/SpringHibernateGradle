/**
 * 
 */

//first we define the volumn to be [512, 512, 394], these values should be get after the recontruction is done. 
var pixelX = 512; 
var pixelY = 512; 
var pixelZ = 394; 

//	Converts a comma-separated ASCII ordinal string list
//  back to an ArrayBuffer (see note for bufferToString())
function getPixelData(pixelDataAsString)
{
	var arr = pixelDataAsString.split(","), pixelData = new Int16Array( arr );
    return pixelData;
}

//load the data from http://10.11.12.33:8080/slice/107205/T/221
function retrieveImageFromServer($, id, viewType, frameNo)
{
	var imageId = "/slice/"+ id + "/" + viewType + "/" + frameNo;
	var url = "/slice/"+ id + "/" + viewType + "/" + frameNo; 

	$.when($.getJSON(currentURL)).done( function(json) 
	{ 
		var pixelDataAsString = json[viewPos].data;
		 
		console.log(pixelDataAsString);
		console.log(json[viewPos].view);
		console.log(json[viewPos].row);
		console.log(json[viewPos].column);
		console.log(json[viewPos].rowspacing);
		console.log(json[viewPos].columnspacing);
		
		var width = json[viewPos].row;
    	var height = json[viewPos].column;

		var rowPixelSpacing = json[viewPos].rowspacing;
        var columnPixelSpacing = json[viewPos].columnspacing;

        /*
		var width = json[viewPos].colum;
    	var height = json[viewPos].row;
        
		var rowPixelSpacing = json[viewPos].columnspacing;
        var columnPixelSpacing = json[viewPos].rowspacing;
 		*/
        
		//"view":"coronal","row":512,"column":394,"rowspacing":0.6640625,"columnspacing":1.0,
	});
        
}
    