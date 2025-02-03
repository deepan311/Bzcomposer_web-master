<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="tree-menu/apytmenu_add.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="dist/jsPdf/jspdf.min.js"></script>
<script type="text/javascript" language="JavaScript1.2" src="dist/jsPdf/html2canvas.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>	
<%@include file="/include/header.jsp"%>
<%-- <title><spring:message code="BzComposer.Report.ReservedInventoryList.ItemPriceList" /></title> --%>
</head>
<body>
<div id="content">
<%-- <img alt="" src="${pageContext.request.contextPath}/ChartReports/AccountReceivablePie${sessionScope.userID}.png"> --%>

<div style="text-align: center;">
	<button type="button" class="btn btn-primary" onclick="printPage()" id="printButton">Print</button>
	<!-- <button id="pdfDownloader">generate PDF</button> -->
</div>

<div style="display: flex;justify-content:center;" id="pdf">
  <img alt="" src="${pageContext.request.contextPath}/ChartReports/IncomeExpencePie${sessionScope.userID}.png">
</div>
<div style="display: flex;justify-content:center;padding-top: 30px" id="pdf1">  
  <img alt="" src="${pageContext.request.contextPath}/ChartReports/IncomeExpenceBar${sessionScope.userID}.png">
</div>

<%@ include file="/WEB-INF/jsp/include/footer.jsp"%>
</div>
</body>
</html>
<script>
function callRefresh(){
	document.forms[0].action = "Item.do?tabid=ItemPriceList";
	document.forms[0].submit();
}
/*  function exportPdf()
{
	var doc = new jsPDF('p', 'pt', 'a4');
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};

	$('#cmd').click(function () {
	    doc.fromHTML($('#pdf').html(), 15, 15, {
	        'width': 170,
	            'elementHandlers': specialElementHandlers
	    });
	    var imgData = canvas.toDataURL('image/png');
	    doc.addImage(imgData, 'PNG', 10, 10);
	    doc.save('sample-file.pdf');
	});
}  */
 /* $("#pdfDownloader").click(function(){
	var doc = new jsPDF('p', 'pt', 'a4');
	var specialElementHandlers = {
	    '#editor': function (element, renderer) {
	        return true;
	    }
	};
	  html2canvas($("#content"), {
	      useCORS: true,
	      onrendered: function(canvas) {
	        var imgData = canvas.toDataURL('image/png');
	        var doc = new jsPDF('p', 'pt', 'a4');
	        doc.addImage(imgData, 'PNG', 10, 10);
	        doc.save('download.pdf');
	      }
	    });
	
	     doc.fromHTML($('#content').html(), 15, 15, {
	        'width': 170,
	            'elementHandlers': specialElementHandlers
	    });
	    var imgData = canvas.toDataURL('image/png');
	    doc.addImage(imgData, 'PNG', 10, 10);
	    doc.save('sample-file.pdf'); 
}); */
 
/*  $(document).ready(function() {

	  $("#pdfDownloader").click(function() {
	    html2canvas($("#content"), {
	      useCORS: true,
	      onrendered: function(canvas) {
	        var imgData = canvas.toDataURL('image/png');
	        var doc = new jsPDF('p', 'pt', 'a4');
	        doc.addImage(imgData, 'PNG', 10, 10);
	        doc.save('download.pdf');
	      }
	    });
	  });
	})


	function saveAs(uri, filename) {
	    var link = document.createElement('a');
	    if (typeof link.download === 'string') {
	      link.href = uri;
	      link.download = filename;

	      //Firefox requires the link to be in the body
	      document.body.appendChild(link);

	      //simulate click
	      link.click();

	      //remove the link when done
	      document.body.removeChild(link);
	    } else {
	      window.open(uri);
	    }
	  }
 */
 function printPage()
 {
	 printButton.style.visibility='hidden';
	 window.print();
	 printButton.style.visibility='visible';
 }
</script>
