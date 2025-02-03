<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Billing</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<style type="text/css">
div#pie { /* 	color:#05A9C5;; */
	padding: 10px 0px 20px 0px;
}

table.tabla-listados {
	width: 100%;
	border: 1px solid rgb(207, 207, 207);
	margin: 20px 0px 20px 0px;
}

table.tabla-listados thead tr th {
	font-size: .7em;
	text-align: left;
	padding: 5px 10px;
	/* 	background: rgba(5, 169, 197, 0.11); */
	border-bottom: 1px solid rgba(5, 169, 197, 0.2);
	/* 	color: #333; */
	text-shadow: #999 0px 1px 1px;
	white-space: nowrap;
}

table.tabla-listados tbody tr td {
	font-size: .8em;
	/* 	color: #666; */
	padding: 5px 0px 5px 14px;
	/* 	border-bottom: 1px solid rgb(207, 207, 207); */
	background: #fff;
	vertical-align: top;
}
#highlight {
	
 		background-color: blue; 
 
	 }
	 
</style>
</head>
<body>

		<div class="content1 clearfix">
			<div class="content-tabs">
		
			<div class="table1" id="tblForInvoiceOrder">
				<table class="table table-bordered table-sm devAcRecDataTbl">
				  <thead class="thead-light">
				    <tr>
				      <th scope="col">Select</th>
				      <th scope="col" class="text-right">REf_No#</th>
				      <th scope="col">Company</th>
				      <th scope="col">Amount</th>
				      <th scope="col" class="text-right">Due_Date</th>
				      <th scope="col" class="text-right">Overdue_days</th>
				      <th scope="col" class="text-right">Email</th>
				      <th scope="col" class="text-right">Term</th>
				      <th scope="col" class="text-right">Address1</th>
				      <th scope="col" class="text-right">Address2</th>
				      <th scope="col" class="text-right">City</th>
				      <th scope="col" class="text-right">State</th>
				      <th scope="col" class="text-right">Zipcode</th>
				      <th scope="col">Memo</th>
				      <th scope="col">Consigned</th>
				    </tr>
				  </thead>
				  
				  <tbody>
				  	<tr>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	<td></td>
				  	</tr>
				  
				  </tbody>
		</table>
		</div>
	</div>	
		</div>

</body>
</html>