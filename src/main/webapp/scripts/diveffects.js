var dhtmlgoodies_slideSpeed = 10;	// Higher value = faster
var dhtmlgoodies_timer = 10;	// Lower value = faster

var objectIdToSlideDown = false;
var dhtmlgoodies_activeId = false;
function showHideContent(e,inputId)
{
		
	if(!inputId)inputId = this.id;
	inputId = inputId + '';
	var numericId = inputId.replace(/[^0-9]/g,'');

	

	var answerDiv = document.getElementById('dhtmlgoodies_a' + numericId);
	
	objectIdToSlideDown = false;
	
	if(!answerDiv.style.display || answerDiv.style.display=='none'){

		if(dhtmlgoodies_activeId &&  dhtmlgoodies_activeId!=numericId){			
			objectIdToSlideDown = numericId;
	
			setTimeout("showspin()",100);
			setTimeout("hidespin()",1500);

			slideContent(dhtmlgoodies_activeId,(dhtmlgoodies_slideSpeed*-1));
			//setTimeout("slideContent("+dhtmlgoodies_activeId+","+(dhtmlgoodies_slideSpeed*-1)+")",3000);
		}else{
			
			setTimeout("showspin()",100);
			setTimeout("hidespin()",1500);

			answerDiv.style.display='block';
			answerDiv.style.visibility = 'visible';
			slideContent(numericId ,dhtmlgoodies_slideSpeed);

			//setTimeout("slideContent("+numericId +"," +dhtmlgoodies_slideSpeed+")",3000);
		}
	}else{
		slideContent(numericId,(dhtmlgoodies_slideSpeed*-1));
		dhtmlgoodies_activeId = false;
	}	
}

function slideContent(inputId,direction)
{
	var obj =document.getElementById('dhtmlgoodies_a' + inputId);
	var contentObj = document.getElementById('dhtmlgoodies_ac' + inputId);
	height = obj.clientHeight;
	height = height + direction;
	rerunFunction = true;
	if(height>contentObj.offsetHeight){
		height = contentObj.offsetHeight;
		rerunFunction = false;
	}
	if(height<=1){
		height = 1;
		rerunFunction = false;
	}

	obj.style.height = height + 'px';
	var topPos = height - contentObj.offsetHeight;
	if(topPos>0)topPos=0;
	contentObj.style.top = topPos + 'px';
	if(rerunFunction){
		setTimeout('slideContent(' + inputId + ',' + direction + ')',dhtmlgoodies_timer);
	}else{
		if(height<=1){
			obj.style.display='none'; 
			if(objectIdToSlideDown && objectIdToSlideDown!=inputId){
				document.getElementById('dhtmlgoodies_a' + objectIdToSlideDown).style.display='block';
				document.getElementById('dhtmlgoodies_a' + objectIdToSlideDown).style.visibility='visible';
				slideContent(objectIdToSlideDown,dhtmlgoodies_slideSpeed);				
			}
		}else{
			dhtmlgoodies_activeId = inputId;
		}
	}
}



function initShowHideDivs()
{
	var divs = document.getElementsByTagName('DIV');
	var divCounter = 1;
	for(var no=0;no<divs.length;no++){
		if(divs[no].className=='dhtmlgoodies_question'){
			divs[no].onclick = showHideContent;
			divs[no].id = 'dhtmlgoodies_q'+divCounter;
			
			var answer = divs[no].nextSibling;
			while(answer && answer.tagName!='DIV'){
				answer = answer.nextSibling;
			}
			
			answer.id = 'dhtmlgoodies_a'+divCounter;	
			
			contentDiv = answer.getElementsByTagName('DIV')[0];
			contentDiv.style.top = 0 - contentDiv.offsetHeight + 'px'; 	
			contentDiv.className='dhtmlgoodies_answer_content';
			contentDiv.id = 'dhtmlgoodies_ac' + divCounter;
			answer.style.display='none';
			answer.style.height='1px';
			divCounter++;
		}		
	}	
	showHideContent(onload,'dhtmlgoodies_a1');
}

function showspin()
{
	
	 document.getElementById('waitMessage').style.display='inline';
	//document.getElementById("commentsSpinner").style.display="";
}
function hidespin()
{
	
	document.getElementById('waitMessage').style.display='none';
	//document.getElementById("commentsSpinner").style.display="none";
}