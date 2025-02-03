<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <link rel="stylesheet" href="/resources/demos/style.css"> 
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/jquery-ui.js"></script>

<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/jquery-barcode.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/jquery-barcode.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/FileSaver.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/FileSaver.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/dist/js/html2canvas.js"></script> 
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<style type="text/css">
#config {
    margin: 10px 0 10px 0px;
}
.config {
    float: left;
    width: 200px;
    height: 250px;
    border: 1px solid #000;
    margin-left: 10px;
}
.config .title {
    font-weight: bold;
    text-align: center;
}
.config .barcode2D, #miscCanvas {
    display: none;
}
#submit {
    clear: both;
}
</style>
<title><spring:message code="BzComposer.coupondesigntitle"/></title>
</head>
<style>
.div
{
	width: auto;
	height: 100;
	border-color: black;
	background-color: gray;
}
.div1
{
	background-color: gray;
}
.couponDesign {
	font-family: segoe ui, sans-serif;
    font-size: 16px;
}
.couponDesign input, .couponDesign button {
	font-family: inherit;
	font-size: inherit
}
.couponDesign .box-content {
	display: flex;
	margin: 0 -5px;
	
}
.couponDesign .box {
	border: 1px solid #ddd;
	display: inline-block;
	padding: 15px;
	width: 50%;
	vertical-align: top;
	margin: 0 5px  5px 5px
}
.couponDesign .box .form-group {
	margin-bottom: 15px;
	display: block;
}
.couponDesign .box .form-control {
	width: 100%
}
.text-simple {
    min-height: 46px;
}
#Target,  #canvasTarget {
margin-top: 20px;
text-align: center;
}
</style>
<body onload="hideElement()">
<div id="resultDiv" class="div">
<center>
		<textarea type="text" id="result" name="result" cols="30" rows="5"  readonly="readonly" style="resize: none;"></textarea>	
</center>
</div>  
<div id="result1" class="div1"></div><br>
<div class="couponDesign">
	<div class="box-content">
		<div class="box">
			<label class="form-group">
				<spring:message code="BzComposer.coupondesign.textstamp"/>
				<!-- Text Stamp --> 
				<div>
					<input class="form-control" type="text" id="stamp">
				</div>
			</label>
			<font id="resultText" style="font-size:20px;">
				<spring:message code="BzComposer.coupondesign.sometext"/>
				<!-- Some Text -->
			</font><br>
			<button class="btn btn-default"  id="stampBtn" name="stampBtn">
				<spring:message code="BzComposer.coupondesign.stamptext"/>
				<!-- Stamp Text -->
			</button>
			<button class="btn btn-default" onclick="showDialog()">
				<spring:message code="BzComposer.coupondesign.font"/>
				<!-- Font -->
			</button>
			<button class="btn btn-default" onclick="showDialog1()">
				<spring:message code="BzComposer.coupondesign.color"/>
				<!-- Color -->
			</button>
		</div>
		<div class="box">
			<label class="form-group">
				<spring:message code="BzComposer.coupondesign.barcodestamp"/>
				<!-- Barcode Stamp --> 
				<div>
					<input class="form-control" type="text" id="barcode">
				</div>
			</label>
			<b>
				<spring:message code="BzComposer.coupondesign.type"/>
				<!-- Type: -->
			</b>
			<input type="radio" name="btype" id="code93" value="code93">
			<label for="code93">
				<spring:message code="BzComposer.coupondesign.code93"/>
				<!-- code 93 -->
			</label>
			&nbsp;&nbsp;
			<input type="radio" name="btype" id="code128" value="code128">
			<label for="code128">
				<spring:message code="BzComposer.coupondesign.code128"/>
				<!-- code 128 -->
			</label>
			<br/>
			<input type="radio" name="btype" id="datamatrix" value="datamatrix">
			<label for="datamatrix">
				<spring:message code="BzComposer.coupondesign.datamatrix"/>
				<!-- Data Matrix -->
			</label>
			<br>
			<br>
			<label for="title">
				<b>
					<spring:message code="BzComposer.coupondesign.format"/>
					<!-- Format: -->
				</b>
			</label>
			<input type="radio" id="css" name="renderer" value="css" checked="checked">
			<label for="css">
				<spring:message code="BzComposer.coupondesign.css"/>
				<!-- CSS -->
			</label>
			<br>
			<button class="btn btn-default" id="barcodeBtn" name="barcodeBtn">
				<spring:message code="BzComposer.coupondesign.stampbarcode"/>
				<!-- Stamp Barcode -->
			</button>
		</div>
	</div>
	<p>
		<spring:message code="BzComposer.coupondesign.coupondesign"/>
		<!-- Coupon size: 4" x 2.5" -->
	</p>
	</div>
	<div></div><div></div>
	<div class="box">
	<a href="#" id="ExportToBMP" name="ExportToBMP">
		<button class="btn btn-default">
			<spring:message code="BzComposer.coupondesign.exportbmpimage"/>
			<!-- Export to BMP Image -->
		</button>
	</a>
	<button class="btn btn-default" id="delText">
		<spring:message code="BzComposer.coupondesign.deleteselection"/>
		<!-- Delete Selection -->
	</button>
	<button class="btn btn-default" onclick="Clear()">
		<spring:message code="BzComposer.coupondesign.clear"/>
		<!-- Clear -->
	</button>
	</div>
	<div id="dialog" style="display:none;">
		<font id="text1" style="font-size:20px;">
			<spring:message code="BzComposer.coupondesign.sometext"/>
			<!-- Some Text -->
		</font>
		<br><br>
    	<b>
    		<spring:message code="BzComposer.coupondesign.font"/>:
			<!-- Font: -->
   		</b>
		<select id="selecth1FontFamily" name="selectFontFamily" onchange="updateh1family();">
	  		<option> Serif </option>
	  		<option> Agency FB </option>
	  		<option> Arial Black </option>
	  		<option> Arial </option>
	  		<option> Castellar </option>
	  		<option> Chiller </option>
	  		<option> Kunstler Script </option>
	  		<option> Sans-Serif </option>                                  
	  		<option> Tahoma </option>
	  		<option> Verdana </option>
	  		<option> Lucida Sans Unicode </option>                               
		</select>
		<br><br>
  		<b>
  			<spring:message code="BzComposer.coupondesign.fontstyle"/>:
  			<!-- Font Style: -->
		</b>
		<select id="FontStyle" name="FontStyle">
    		<option> Bold </option>
    		<option> Italic </option>
    		<option> Underline </option>
    		<option> Plain </option>                               
  		</select>
  		<br><br>
    	<b>
    		<spring:message code="BzComposer.coupondesign.fontsize"/>:
    		<!-- Font Size: -->
   		</b>
   		<input type="number" id="size" min="0" max="70" value="30" onchange="updateSize()" />
    	<br><br>
    	<center><input type="submit" id="Apply" name="Apply" value="save"/></center>
  </div>
  <div id="dialog1" style="display:none;">
    	<b>
    		<spring:message code="BzComposer.coupondesign.fontcolor"/>:
    		<!-- Font Color: -->
   		</b>
   		<input type="color" onchange="updateColor()" id="color" />
    	<br><br>
    	<center><input type="submit" id="Apply1" name="Apply1" value="save"/></center>
  </div>
</body>
<script type="text/javascript">
document.querySelector('textarea').addEventListener('mouseup', function () {
	  window.mySelection = this.value.substring(this.selectionStart, this.selectionEnd)
	});
/* 
*/
$(document).ready(function()
{
	var element; 
	var getCanvas; 
	 
	$("#stampBtn").on('click', function () {
	if(document.getElementById("stamp").value == "")
	{

		alert("<spring:message code='BzComposer.coupondesign.entertexttostamp'/>")
	}
	else
	{
		
		element = $("#result");
	    document.getElementById("resultDiv").style.display = "block";
	    document.getElementById("result1").style.display = "none";
	    var stamp = document.getElementById("stamp").value;
	    if(document.getElementById("result").value)
	    {
			var currentVal = $('#result').val();
	    	var newVal = currentVal + $("#stamp").val();
			document.getElementById("result").value = newVal;
    	}
    	else
   		{
    		document.getElementById("result").value = stamp;
   		}
    	document.getElementById("resultText").value = stamp;
    	html2canvas(element, {
	         onrendered: function (canvas) {
	                $("#result").append(canvas);
	                getCanvas = canvas;
            }
         });
		}
   	});
	
	$("#Apply").on('click', function () {
		var font = $('#selecth1FontFamily').val();
		var size = $('#size').val();
		var selector1 = document.getElementById('FontStyle');
		  var family = selector1.options[selector1.selectedIndex].value;
		  if (family == "Bold"){
	        $("#resultText").css('font-weight', 'bold');
	        $("#text1").css('font-weight', 'bold');
	        $("#result").css('font-weight', 'bold');
	        
	        document.getElementById('result').style.fontFamily = font;
		  	document.getElementById('resultText').style.fontFamily = font;
	  		document.getElementById('text1').style.fontFamily = font;
	  		
	  		document.getElementById('result').style.fontSize = document.getElementById('size').value + "px";
	  		document.getElementById('resultText').style.fontSize = document.getElementById('size').value + "px";
	      }
	      else if (family == "Italic"){
	        $("#resultText").css('font-style', 'italic');
	        $("#text1").css('font-style', 'italic');
	        $("#result").css('font-style', 'italic');
	        
	        document.getElementById('result').style.fontFamily = font;
	  		document.getElementById('resultText').style.fontFamily = font;
	  		document.getElementById('text1').style.fontFamily = font;
	  		
	  		document.getElementById('result').style.fontSize = document.getElementById('size').value + "px";
	  		document.getElementById('resultText').style.fontSize = document.getElementById('size').value + "px";
	      }
	      else if (family == "Underline"){
			$("#resultText").css('text-decoration', 'underline');
	        $("#text1").css('font-decoration', 'underline');
	        $("#result").css('text-decoration', 'underline'); 
	        
			document.getElementById('result').style.fontFamily = font;
	  		document.getElementById('resultText').style.fontFamily = font;
	  		document.getElementById('text1').style.fontFamily = font;
	  		document.getElementById('result').style.fontSize = document.getElementById('size').value + "px";
	  		document.getElementById('resultText').style.fontSize = document.getElementById('size').value + "px";
	      }
	      else
	   	  {
	    	$("#resultText").css('font-style', 'normal');
	    	$("#text1").css('font-style', 'normal');
	    	$('#result').css('font-style', 'normal');
	    	document.getElementById('result').style.fontFamily = font;
	  		document.getElementById('resultText').style.fontFamily = font;
	  		document.getElementById('text1').style.fontFamily = font;
	  		
	  		document.getElementById('result').style.fontSize = document.getElementById('size').value + "px";
	  		document.getElementById('resultText').style.fontSize = document.getElementById('size').value + "px";
	   	  }
		element = $("#result");
		html2canvas(element, {
	        onrendered: function (canvas) {
	               $("#result").append(canvas);
	               getCanvas = canvas;
	       }
	    });
	}); 
	
	$("#Apply1").on('click', function () {
		var color = $('#color').val();
		
		document.getElementById('result').style.color = document.getElementById('color').value;
		
		document.getElementById('resultText').style.color = document.getElementById('color').value;
		
		 element = $("#result");
		html2canvas(element, {
	        onrendered: function (canvas) {
	               $("#result").append(canvas);
	               getCanvas = canvas;
	       }
	    }); 
	}); 
	
	
	$("#delText").on('click', function () {
		var ele  = document.getElementById('result');
		 var text = ele.value;

		 text = text.slice(0, ele.selectionStart) + text.slice(ele.selectionEnd);
		 ele.value = text;
		 debugger;
		 element = $("#result");
		 debugger;
		 html2canvas(element, {
	         onrendered: function (canvas) {
	                $("#result").append(canvas);
	                getCanvas = canvas;
	        }
		 
	     });
	});
	
	$("#barcodeBtn").on('click', function(){
		element = $("#result1");
		var btype = $("input[name=btype]:checked").val();
		if(document.getElementById("barcode").value == "")
		{

			alert("<spring:message code='BzComposer.coupondesign.entertexttogeneratebarcode'/>");
		}
		if(!btype)
		{

			alert("<spring:message code='BzComposer.coupondesign.selectbarcodestyle'/>");
		}
		else
		{
			var value = document.getElementById("barcode").value;
			var renderer = $("input[name=renderer]:checked").val();
			var quietZone = false;
		  	var settings = {
		  		output:renderer,
		  		bgColor: "#FFFFFF",
		        color: "#000000",
		        barWidth: 1,
		    	barHeight: 50,
		        moduleSize: 5,
		        posX: 10,
		        posY: 20,
		        addQuietZone: 1
		        };
		        if ($("#rectangular").is(':checked') || $("#rectangular").attr('checked'))
		        {
		        	value = {code:value, rect: true};
		        }
		        $("#result1").html("").show().barcode(value, btype, settings); 
		         html2canvas(element, {
			         onrendered: function (canvas) {
			        	   $("#result1").html("").show("");  
			                 $("#result1").append(canvas);
			                 getCanvas = canvas; 
			             }
			         });
		        document.getElementById("resultDiv").style.display = "none";
			}
		});

		$("#ExportToBMP").on('click', function () {
		 element = $("#result1");
	    var imgageData = getCanvas.toDataURL("image/png");
	    var newData = imgageData.replace(/^data:image\/png/, "data:application/octet-stream");
	    $("#ExportToBMP").attr("download", "Image.bmp").attr("href", newData);
		});
		
	});
function hideElement()
{
	document.getElementById("resultDiv").style.display = "none";
}

function showConfig1D(){
    $('.config .barcode1D').show();
    $('.config .barcode2D').hide();
  }
  
  function showConfig2D(){
    $('.config .barcode1D').hide();
    $('.config .barcode2D').show();
  }
function add()
{
	var text = document.getElementById("stamp").value;
	document.getElementById("result").value = text;
}
function Clear()
{
	if(document.getElementById("result").value)
	{ 
		document.getElementById("result").value="";
	}
	document.getElementById("result1").style.display = 'none';
}
$(function(){
    $('input[name=btype]').click(function(){
      if ($(this).attr('id') == 'datamatrix') showConfig2D(); else showConfig1D();
    });
    $('input[name=renderer]').click(function(){
      if ($(this).attr('id') == 'canvas') $('#miscCanvas').show(); else $('#miscCanvas').hide();
    });
    generateBarcode();
  });
  
function updateh1family() 
{
	var selector = document.getElementById('selecth1FontFamily');
	var family = selector.options[selector.selectedIndex].value;
}

function updateSize() 
{
	document.getElementById('resultText').style.fontSize = document.getElementById('size').value + "px";
}

function updateColor() 
{
	document.getElementById('resultText').style.color = document.getElementById('color').value;
}

function showDialog() 
{
	$("#dialog").dialog();
}

function showDialog1() 
{
	$("#dialog1").dialog();
}
</script>
</html>