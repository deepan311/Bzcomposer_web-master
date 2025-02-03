var wghtArr = new Array(100);
var itemArr = new Array(100);
var qtyArr = new Array(100);
var upriceArr = new Array(100);
var codeArr = new Array(100);
var taxArr = new Array(100);
var descArr = new Array(100);
var uwghtArr = new Array(100);
var serialArr = new Array(100);
var itmIDArr = new Array(100);
var itmOrdArr = new Array(100);

deleted = 0;
index1=0;
cnt=0;
count=0;
function Pending_Value(form){
			if(form.isPending.checked==true){
				form.isPending.value="on";
			}
			else{
				form.isPending.value="off";
			}
		
		}
		function TaxaValue(form){
			if(form.taxable.checked==true){
				form.taxable.value="on";
			}
			else{
				form.taxable.value="off";
			}
		}
		
		function ShippedItem(form){
			if(form.itemShipped.checked==true){
				form.itemShipped.value="on";
			}
			else{
				form.itemShipped.value="off";
			}
		}
		
		function Assignment(value,form){
			if(value==0){
				form.billTo.value="";
				form.shipTo.value="";
			}
			else{		
				size=document.getElementById("bSize").value;
				shSize=document.getElementById("sSize").value;
				var i;
				for(i=0;i<size;i++){
					var field1 = document.getElementById(i+"id").value;
					if(value==field1){
						document.getElementById('cid').value=value;
						form.companyID.value=document.getElementById(i+"cid").value;
						form.bsAddressID.value=document.getElementById(i+"bsaddr").value;
						form.billTo.value=document.getElementById(i+"bl").value;
						break;		
					}
				}
				for(i=0;i<size;i++){
					var field2 = document.getElementById(i+"sh_id").value;
					if(value==field2){
						form.shipTo.value=document.getElementById(i+"sh").value;
						break;		
					}
				} 
			}
		}
	
		
		function StyleChange(value){
			document.getElementById('invStyle').value=value;
			size=document.getElementById('CartSize').value;
			hidn_val= document.getElementById('hidn').value;
			if(value==0){
				product();
					/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='block';		
				document.getElementById('td16').style.display='block';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				productTable(size);
				for(x=0;x<hidn_val;x++)
					productItem(x);
				
					
			}
			if(value!=0){
				document.getElementById('InvStyle').value=value;
				if(value==1){
					service();
					/*QTY */
				document.getElementById('td4').style.display='block';
				document.getElementById('td3').style.display='none';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='block';
				document.getElementById('td10').style.display='none';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='none';		
				document.getElementById('td14').style.display='none';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				/* item in table */
				var i;
				for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='visible';
					document.getElementById(i+"amt").style.visibility='hidden';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='hidden';
				}
				
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='none;'
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='hidden';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='block';
					document.getElementById("it42").style.display='block';
					
				
					for(x=0;x<hidn_val;x++)
						serviceItem(x);
	
				
				}
				else if(value==2){
					quick(size);
					for(x=0;x<hidn_val;x++)
						quickItem(x);
				}
				
				
				else if(value==3){
					manufacture(size)
					for(x=0;x<hidn_val;x++)
						manufactureItem(x);
				}
							
				else if(value==4){
					product();
					productTable(size);
					for(x=0;x<hidn_val;x++)
						productItem(x);
				
					/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='block';		
				document.getElementById('td16').style.display='block';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				}
				else if(value==5){
					finance(size);
					for(x=0;x<hidn_val;x++)
						financeItem(x);
				}
				
				
				else if(value==6){
				professional(size);
					for(x=0;x<hidn_val;x++){
						quickItem(x);
					}
				}
				
				else if(value==7){
					ebusiness(size);
					for(x=0;x<hidn_val;x++){
						ebusinessItem(x);
					}
						
				}
			}
		}
		
		function service(){
			/* hidden field for ship to */
				document.getElementById('ship_label').style.visibility="hidden";
				document.getElementById('ship_id').style.visibility="hidden";
				
				
				/* hidden field for P.O. Num */
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
				
				/* hidden field for ship date */
				document.getElementById('sh_date_label').style.visibility="hidden";
				document.getElementById('sh_date_id').style.visibility="hidden";
				
				/* hidden field for via & rep */
				document.getElementById('td1').style.visibility="hidden";
				
				/* Visible field for bill to */
				document.getElementById('bill_label').style.visibility="visible";
				document.getElementById('bill_id').style.visibility="visible";
				
				/* Visible field for Term & Payment */
				document.getElementById('td2').style.visibility="visible";		
				
				
				
				
		}
		
		
		function quick(size){
			/* hidden field for ship to */
				document.getElementById('ship_label').style.visibility="hidden";
				document.getElementById('ship_id').style.visibility="hidden";
				
				/* hidden field for P.O. Num */
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
				
				/* hidden field for ship date */
				document.getElementById('sh_date_label').style.visibility="hidden";
				document.getElementById('sh_date_id').style.visibility="hidden";
				
				/* hidden field for via & rep */
				document.getElementById('td1').style.visibility="hidden";
				
				/* hidden field for bill to */
				document.getElementById('bill_label').style.visibility="hidden";
				document.getElementById('bill_id').style.visibility="hidden";
				
				/* hidden field for Term & Payment */
				document.getElementById('td2').style.visibility="hidden";
				
				/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				/* Item in Table  */
				quickTable(size);
						
		}
		
		function manufacture(size){
			product();
							/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='block';
				document.getElementById('td7').style.display='block';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
						
				/* Item in Table  */
				 var i;
				for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='visible';
					document.getElementById(i+"amt").style.visibility='visible';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='visible';
				}
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='block';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='visible';
				
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
					
		}
		
		function product(){
			/* visible field for ship to */
				document.getElementById('ship_label').style.visibility="visible";
				document.getElementById('ship_id').style.visibility="visible";
				
				/* visible field for P.O. Num */
				document.getElementById('po_num_label').style.visibility="visible";
				document.getElementById('po_num_id').style.visibility="visible";
				
				/* visible field for ship date */
				document.getElementById('sh_date_label').style.visibility="visible";
				document.getElementById('sh_date_id').style.visibility="visible";
				
				/* Visible field for bill to */
				document.getElementById('bill_label').style.visibility="visible";
				document.getElementById('bill_id').style.visibility="visible";
				
				/* Visible field for Term & Payment */
				document.getElementById('td2').style.visibility="visible";	
				
				/* visible field for via & rep */
				document.getElementById('td1').style.visibility="visible";
				
					
		}
		
		function productTable(size){
			var i;
			for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='visible';
					document.getElementById(i+"amt").style.visibility='visible';
					document.getElementById(i+"wgt").style.visibility='visible';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='hidden';
			}
			document.getElementById("it1").style.visibility='visible';
			document.getElementById("it2").style.display='block';
			document.getElementById("it3").style.visibility='visible';
			document.getElementById("it4").style.display='block';
			document.getElementById("it5").style.visibility='visible';
			document.getElementById("it6").style.visibility='visible';
			document.getElementById("it7").style.visibility='visible';
			document.getElementById("it8").style.visibility='hidden';
			document.getElementById("it22").style.display='none';
			document.getElementById("it42").style.display='none';
			
			value=document.getElementById('hidn').value;
			var j;
			for(j=0;j<value;j++){
				document.getElementById(j+'1').style.visibility='visible';
				document.getElementById(j+'2').style.visibility='visible';
				document.getElementById(j+'3').style.visibility='hidden';
				document.getElementById(j+'4').style.visibility='visible';
				document.getElementById(j+'5').style.visibility='visible';
				document.getElementById(j+'6').style.visibility='visible';
				document.getElementById(j+'7').style.visibility='visible';
				document.getElementById(j+'8').style.visibility='visible';
			}
		}
		
		function finance(size){
			service();
				/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='none';		
				document.getElementById('td5').style.display='none';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='none';
				document.getElementById('td12').style.display='none';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				/* Item in Table  */
				 var i;
				for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='hidden';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='hidden';
					document.getElementById(i+"amt").style.visibility='visible';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='hidden';
				}
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='none';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
						
		}
		
		function professional(size){
			service();
			/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='block';
				document.getElementById('td12').style.display='block';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='block';
				document.getElementById('td18').style.display='block';
				
				quickTable(size);
				
		}
		
		function quickTable(size){
			/* Item in Table  */
				 var i;
				for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='visible';
					document.getElementById(i+"amt").style.visibility='visible';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='visible';
					document.getElementById(i+"serial").style.visibility='hidden';
				}
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='block';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
				
				value=document.getElementById('hidn').value;
				var j;
				for(j=0;j<value;j++){
					document.getElementById(j+'1').style.visibility='visible';//code
					document.getElementById(j+'2').style.visibility='visible';//qty
					document.getElementById(j+'3').style.visibility='hidden';//serial No
					document.getElementById(j+'4').style.visibility='visible';//desc
					document.getElementById(j+'5').style.visibility='visible';//unit price
					document.getElementById(j+'6').style.visibility='visible';//amount
					document.getElementById(j+'7').style.visibility='hidden';//weight
					document.getElementById(j+'8').style.visibility='visible';//tax
				}
		}
		function ebusiness(size){
			/* visible field for ship to */
				document.getElementById('ship_label').style.visibility="visible";
				document.getElementById('ship_id').style.visibility="visible";
				
				/* visible field for ship date */
				document.getElementById('sh_date_label').style.visibility="visible";
				document.getElementById('sh_date_id').style.visibility="visible";
				
				/* visible field for via & rep */
				document.getElementById('td1').style.visibility="visible";
				
				/* visible field for term & payment method */
				document.getElementById('td1').style.visibility="visible";
				
				/* hidden field for P.O. Num */
				document.getElementById('po_num_label').style.visibility="hidden";
				document.getElementById('po_num_id').style.visibility="hidden";
								
				/* hidden field for bill to */
				document.getElementById('bill_label').style.visibility="hidden";
				document.getElementById('bill_id').style.visibility="hidden";
				
				/*QTY */
				document.getElementById('td4').style.display='none';
				document.getElementById('td3').style.display='block';		
				document.getElementById('td5').style.display='block';
				/*Serial No */
				document.getElementById('td6').style.display='none';
				document.getElementById('td7').style.display='none';
				/* DESC */	
				document.getElementById('td8').style.display='block';
				document.getElementById('td9').style.display='block';
					
				/* Unit Price/RatePrice*/		
				document.getElementById('td11').style.display='none';
				document.getElementById('td10').style.display='none';
				document.getElementById('td12').style.display='none';
				/* Amount*/
				document.getElementById('td13').style.display='block';		
				document.getElementById('td14').style.display='block';
				/* Weight*/	
				document.getElementById('td15').style.display='none';		
				document.getElementById('td16').style.display='none';
				/* Tax */	
				document.getElementById('td17').style.display='none';
				document.getElementById('td18').style.display='none';
				
				/* Item in Table  */
				 var i;
				for(i=0;i<size;i++){
					document.getElementById(i+"icode").style.visibility='visible';
					document.getElementById(i+"qty").style.visibility='visible';
					document.getElementById(i+"desc").style.visibility='visible';
					document.getElementById(i+"iprice").style.visibility='hidden';
					document.getElementById(i+"amt").style.visibility='visible';
					document.getElementById(i+"wgt").style.visibility='hidden';
					document.getElementById(i+"itax").style.visibility='hidden';
					document.getElementById(i+"serial").style.visibility='hidden';
				}
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='hidden';
					document.getElementById("it8").style.visibility='hidden';
				
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
					
		}
		
		function TaxValue(value,form){
			tot = form.shipping.value;
			subtotal = form.subtotal.value;
			
   			
			size=document.getElementById("tSize").value;
			if(value==0){
				document.getElementById('tax_field').innerHTML="0.0 %";
				rate = 0;
				document.getElementById('tax_val').value=rate;
			}
			else{
				for(i=0;i<size;i++){
					var field = document.getElementById(i+"tx_id").value;
					if(value==field){
						rt = document.getElementById(i+"tx_rt").value;
						document.getElementById('tax_field').innerHTML=rt+" %";
						rate = ( ((subtotal/1 ) * (rt/1)) / 100 );	
						document.getElementById('tax_val').value=rate;		
						break;		
					}
				}
			}
			form.tax.value=rate;
			total = ((tot/1) + (subtotal/1)+(rate)/1);
			form.total.value=total;
		}
		
		
		function AddItem(form){
			if(form.itemID.value==0){
				document.getElementById('serialNo_id').value="";
				document.getElementById('qty_id').value="";
				document.getElementById('desc_id').value="";
				document.getElementById('unitPrice_id').value="";
				document.getElementById('amount_id').value="";
				document.getElementById('weight_id').value="";
				alert("Select Item first");
								
			}
			else{		
				style = document.getElementById('invStyle').value;
				wt = form.weight.value;
				hidn_val= document.getElementById('hidn').value;
				
				
				var tr = document.createElement("tr");
				tr.setAttribute("id", "tr"+hidn_val);
			
				var tr2 = document.getElementById('tr##');
				var parentTr = tr2.parentNode;
				parentTr.insertBefore(tr, tr2);
				
				serialNo = document.getElementById('serialNo_id').value;
				var desc = document.getElementById('desc_id').value;
				weight = document.getElementById('weight_id').value;
				tax = document.getElementById('tax_id').value;
				ivcode = document.getElementById('code11').value;
				var t="";
				if(tax==0)
					t="No";
				else
					t="Yes";
					
				qty=document.getElementById('qty_id').value;
				uprice=document.getElementById('unitPrice_id').value;
				
				var td1 = document.createElement("td");
				td1.setAttribute("align", "center");
				td1.setAttribute("id",hidn_val+"1");
				tr.appendChild(td1);
   				td1.innerHTML=ivcode;
   				
   				var td2 = document.createElement("td");
				td2.setAttribute("align", "center");
				td2.setAttribute("id",hidn_val+"2");
				tr.appendChild(td2);
   				td2.innerHTML=qty;
								
				var td3 = document.createElement("td");
				td3.setAttribute("align", "center");
				td3.setAttribute("id",hidn_val+"3");
				tr.appendChild(td3);
				td3.innerHTML=serialNo;
				
				var td4 = document.createElement("td");
				td4.setAttribute("align", "center");
				td4.setAttribute("id",hidn_val+"4");
				tr.appendChild(td4);
   				td4.innerHTML=desc;
   			
   				var td5 = document.createElement("td");
				td5.setAttribute("align", "center");
				td5.setAttribute("id",hidn_val+"5");
				tr.appendChild(td5);
   				td5.innerHTML=uprice;
			
				amt=( (qty/1) * (uprice/1) );
				document.getElementById('amount_id').value=truncate(amt);
						
				var td6 = document.createElement("td");
				td6.setAttribute("align", "center");
				td6.setAttribute("id",hidn_val+"6");
				tr.appendChild(td6);
   				td6.innerHTML=truncate(amt);
   			
	   			var td7 = document.createElement("td");
				td7.setAttribute("align", "center");
				td7.setAttribute("id",hidn_val+"7");
				tr.appendChild(td7);
   				td7.innerHTML=weight;
   				
   				subtotal= form.subtotal.value;
				subtotal = truncate((subtotal/1) + (amt/1));
				form.subtotal.value=subtotal;
				tot=(form.shipping.value);
				total = truncate((tot/1) + (subtotal/1));
				form.total.value=total;
   			
				form.subtotal.value=subtotal;
				document.getElementById('amt_id').value=subtotal;
   				
   				var td8 = document.createElement("td");
				td8.setAttribute("align", "center");
				td8.setAttribute("id",hidn_val+"8");
				tr.appendChild(td8);
				td8.innerHTML=t;
				
				var button='<img onclick="DeleteRow1('+hidn_val+',this.form);"  src="<bean:write name="path" property="pathvalue"/>/images/delete.png" title="Delete" size="8"/>';
				
				var td9 = document.createElement("td");
				td9.setAttribute("align", "center");
				tr.appendChild(td9);
				td9.innerHTML=button;
				
				
				
							
				val=(( wt / 1 ) + (weight/1) );
				
				
				itemVal=document.getElementById('itmVal').value;
				//wghtArr[indx]=val;
				itemArr[index1]=itemVal;
				qtyArr[index1]=qty;
				upriceArr[index1]=uprice;
				codeArr[index1]=ivcode;
				taxArr[index1]=tax;
				descArr[index1]=desc;
				uwghtArr[index1]=weight;
				serialArr[index1]=serialNo;
				itmIDArr[index1]=document.getElementById('itmId').value;
				
				
				index1++;
				
									
				
				form.weight.value=val;
				form.wt.value=val;
				if(style==0 || style==4){//Product/select
					
					productItem(hidn_val);
				}
				if(style==1){//service
					serviceItem(hidn_val);
	
				}
				if(style==2 || style==6){//Quick/Professional
					quickItem(hidn_val);
				}
				
				if(style==3){
					manufactureItem(hidn_val);
				}
				
				if(style==5){//Finance
					financeItem(hidn_val);
				}
				
				if(style==7){//Ebusinness
					ebusinessItem(hidn_val);
				}
				//form.amount.value+=amt+";"
				
				hidn_val=( (hidn_val/1) + 1);

				document.getElementById('hidn').value=hidn_val;
				
				
				
				
			
				
   			}	
		}
		
		function productItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='hidden';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='visible';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='visible';
					document.getElementById(hidn_val+"6").style.visibility='visible';
					document.getElementById(hidn_val+"7").style.visibility='visible';
					document.getElementById(hidn_val+"8").style.visibility='visible';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='block';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='visible';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
		}
		
		function serviceItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='hidden';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='visible';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='visible';
					document.getElementById(hidn_val+"6").style.visibility='hidden';
					document.getElementById(hidn_val+"7").style.visibility='hidden';
					document.getElementById(hidn_val+"8").style.visibility='visible';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='none;'
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='hidden';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='block';
					document.getElementById("it42").style.display='block';
		}
		
		function quickItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='hidden';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='visible';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='visible';
					document.getElementById(hidn_val+"6").style.visibility='visible';
					document.getElementById(hidn_val+"7").style.visibility='hidden';
					document.getElementById(hidn_val+"8").style.visibility='visible';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='block';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
		}
		
		function manufactureItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='visible';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='visible';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='visible';
					document.getElementById(hidn_val+"6").style.visibility='visible';
					document.getElementById(hidn_val+"7").style.visibility='hidden';
					document.getElementById(hidn_val+"8").style.visibility='visible';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='block';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='visible';
				
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
		}
		
		function financeItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='hidden';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='hidden';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='hidden';
					document.getElementById(hidn_val+"6").style.visibility='visible';
					document.getElementById(hidn_val+"7").style.visibility='hidden';
					document.getElementById(hidn_val+"8").style.visibility='visible';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='none';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='visible';
					document.getElementById("it8").style.visibility='hidden';
					
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
		}
		
		function ebusinessItem(hidn_val){
					document.getElementById(hidn_val+"3").style.visibility='hidden';
					document.getElementById(hidn_val+"1").style.visibility='visible';
					document.getElementById(hidn_val+"2").style.visibility='visible';
					document.getElementById(hidn_val+"4").style.visibility='visible';
					document.getElementById(hidn_val+"5").style.visibility='hidden';
					document.getElementById(hidn_val+"6").style.visibility='visible';
					document.getElementById(hidn_val+"7").style.visibility='hidden';
					document.getElementById(hidn_val+"8").style.visibility='hidden';
					
					document.getElementById("it1").style.visibility='visible';
					document.getElementById("it2").style.display='block';
					document.getElementById("it3").style.visibility='visible';
					document.getElementById("it4").style.display='none';
					document.getElementById("it5").style.visibility='visible';
					document.getElementById("it6").style.visibility='hidden';
					document.getElementById("it7").style.visibility='hidden';
					document.getElementById("it8").style.visibility='hidden';
				
					document.getElementById("it22").style.display='none';
					document.getElementById("it42").style.display='none';
			
		}
		
		
		function ItemChange(value){
			var size = document.getElementById('itemSize').value;
			var count;
			for(count=0;count<size;count++){
				var invID = document.getElementById(count+'inv').value;
				if(value==invID){
					var category = document.getElementById(count+'cat').value;
					if(category==1){
						//document.getElementById('serialNo_id').value="";
						document.getElementById('qty_id').value="";
						document.getElementById('desc_id').value="";
						document.getElementById('unitPrice_id').value="";
						document.getElementById('amount_id').value="";
						document.getElementById('weight_id').value="";
						document.getElementById('tax_id').value="";
						
						document.getElementById('qty_id').readonly="true";
						document.getElementById('unitPrice_id').readonly="true";
						document.getElementById('weight_id').readonly="true";
						document.getElementById('code11').value=document.getElementById(count+'code').value;
					}
					else{
						var qty=1;
						var uprice = document.getElementById(count+'price').value;
						var serialNo = document.getElementById(count+'serial').value;
						document.getElementById('serialNo_id').readonly="false";
						document.getElementById('qty_id').readonly="false";
						document.getElementById('unitPrice_id').readonly="false";
						document.getElementById('weight_id').readonly="false";
						
						document.getElementById('serialNo_id').value=serialNo;
						document.getElementById('qty_id').value=qty;
						document.getElementById('desc_id').value=document.getElementById(count+'desc').value;
						document.getElementById('unitPrice_id').value=uprice;
						amt=truncate((qty/1)*(uprice/1));
						document.getElementById('amount_id').value=amt;
						document.getElementById('weight_id').value=document.getElementById(count+'wt').value;
						document.getElementById('code11').value=document.getElementById(count+'code').value;
						document.getElementById('itmId').value=document.getElementById(count+'itmId').value;
						document.getElementById('itmVal').value=value;
					}
				}				
			}
		}
		
		function Multiplication(){
			var qty=document.getElementById('qty_id').value;
			var uprice = document.getElementById('unitPrice_id').value;
			
			var amount=qty*uprice;
			document.getElementById('amount_id').value=amount;				
			
		}
		
		function AddTotal(form){
			value=prompt("Enter Adjusted Amount","");
			alert(value);
			form.adjustedtotal.value=value;
		}
				
		function Init(){
			alert("teddtrtrtdrttr");
			
			<logic:present name="Style">
				StyleChange(<bean:write name="Style"/>);
			</logic:present>
			<logic:notPresent name="Style">
					StyleChange(0);
			</logic:notPresent>
			var i;
			for(i=0;i<100;i++){
				deleted[i]=0;
			}
			for(j=0;j<100;j++){
				wghtArr[j]=0;
				itemArr[j]=0;
				qtyArr[j]=0;
				upriceArr[j]=0;
				codeArr[j]=0;
				taxArr[j]=0;
				descArr[j]=0;
				uwghtArr[j]=0;
				serialArr[j]=0;
				itmIDArr[j]=0;
				
			}
		}
	
		function onSave(form){
			
			No=form.orderNo.value;
			cid = form.custID.value;
			if(cid==0){
				alert("Select customer first");
			}
			else{
				if(No.length==0 || No==0){
					alert("Order No. should not be blank OR zero");
				}
				else{
				  var x=window.confirm("Do you want to save/update Invoice?");
				  if (x){
					subtotal=form.subtotal.value;
					value = form.taxID.value;
					sze=document.getElementById("tSize").value;
					var rt=0;
					for(i=0;i<sze;i++){
						var field = document.getElementById(i+"tx_id").value;
						if(value==field){
							rt = document.getElementById(i+"tx_rt").value;
							document.getElementById('tax_field').innerHTML=rt+" %";
							rt = ((((subtotal)/1 ) * (rt/1)) / 100 );
							document.getElementById('tax_val').value=rt;
							break;		
						}
					}
					subtotal = form.subtotal.value;
					shipping = form.shipping.value;
					
					total = ( (rt/1) + (subtotal/1) + (shipping/1));
					
					form.total.value=total;
					form.tax.value=rt;
					val1 = document.getElementById('hidn').value;
					val =((val1)/1 - (deleted)/1);
					form.size.value=val/1;
					var x;
					
					var idV=0;
					for(x=0;x<val1;x++){
						value = itemArr[x];
						if(value!=-1){
						
							form.item.value+=itemArr[x]+";";
							form.qty.value+=qtyArr[x]+";";
							form.uprice.value+=upriceArr[x]+";";
							form.code.value+=codeArr[x]+";";
							form.isTaxable.value+=taxArr[x]+";";
							form.desc.value+=descArr[x]+";";
							form.unitWeight.value+=uwghtArr[x]+";";
							form.wgt.value+="0"+";";
							form.serialNo.value+=serialArr[x]+";";
				
							form.itemTypeID.value+=itmIDArr[x]+";";
							form.itemOrder.value+=idV+";";
							idV++;
						}
					}
					
					csize = document.getElementById('CartSize').value		
									
					if(csize!=0){
						val=((csize/1) + (val)/1) ;
						var i;
						ordVal = ( ((document.getElementById('hidn').value)/1) + 1 );
				 							
						for(i=0;i<csize;i++){
						rowid=document.getElementById(i+'delt').value
						if(rowid=="del"){
							cnt++;
						}
						else if(rowid=="0"){
							form.code.value+=document.getElementById(i+"invCode").value+";";
							form.qty.value+= document.getElementById(i+"qty").value+";";
							form.desc.value+= document.getElementById(i+"invDesc").value+";";
							form.uprice.value+= document.getElementById(i+"uprice").value+";";
							form.unitWeight.value+= document.getElementById(i+"weight").value+";";
							form.wgt.value+="0"+";";
					
							form.serialNo.value+=document.getElementById(i+"serial").value+";";
					
							itid=document.getElementById(i+'itId11').value;
							form.itemTypeID.value+=itid+";";
							form.itemOrder.value+=idV+";";
							valTax=document.getElementById(i+"tax").value;
							if(valTax=="Yes"){
						
								form.isTaxable.value+="1"+";";
							}
							else{
						
								form.isTaxable.value+="0"+";";
							}
							idV++;
					
							itemVal=document.getElementById(i+'invID11').value;
					
							form.item.value+=itemVal+";";
					
						}
						}
						val=((((val)/1 - (cnt)/1)));
						form.size.value=val;
					}
					
					document.forms[0].action="Invoice.do?tabid=SaveInvoice";
					document.forms[0].submit();
				  }
				}
			}
			
			
		}
		
		
		function NewInvoice(){
			document.forms[0].action="Invoice.do?tabid=Invoice";
			document.forms[0].submit();
		}
		
		function onDelete(form){
			
			No=form.orderNo.value;
			cid = form.custID.value;
			if(cid==0){
				alert("Select customer first");
			}
			else{
				if(No.length==0 || No==0){
					alert("Order No. should not be blank OR zero");
				}
				else{
					var x=window.confirm("Do you want to delete Invoice?")
					if (x){
						document.forms[0].action="Invoice.do?tabid=DeleteInvoice";
						document.forms[0].submit();
					}
				}
			
			}
			
		}
		
		
		
		function ShowUpdate(form){
			cid=form.custID.value;
			if(cid==0){
				alert("Please Select Customer to update information");
			}
			else{
				window.open("Invoice.do?tabid=ShowInvoiceUpdate&CustId="+cid,null,"scrollbars=yes,height=500,width=1050,status=yes,toolbar=no,menubar=no,location=no" );
			}
		}
		function FirstInvoice(){
			
			document.forms[0].action="Invoice.do?tabid=FirstInvoice";
			document.forms[0].submit();
			
		}
		function LastInvoice(){
			document.forms[0].action="Invoice.do?tabid=LastInvoice";
			document.forms[0].submit();
		}
		
		function PreviousInvoice(){
			document.forms[0].action="Invoice.do?tabid=PreviousInvoice";
			document.forms[0].submit();
		}
		
		function NextInvoice(){
			document.forms[0].action="Invoice.do?tabid=NextInvoice";
			document.forms[0].submit();
		}
		
		function paymentHistory(form){
			cid=form.custID.value;
			if(cid==0){
				alert("Please Select Customer");
			}
			else{
				window.open("Invoice.do?tabid=PaymentHistory&CustId="+cid,null,"scrollbars=yes,height=500,width=1050,status=yes,toolbar=no,menubar=no,location=no" );
			}
		}
		function numbersonly(e,val)
		{
			var temp=val.indexOf(".");

			var unicode=e.charCode? e.charCode : e.keyCode;


			if (unicode!=8)
			{
				 //if the key isn't the backspace key (which we should allow)

				if(unicode==46 && temp==-1)
				{
					 return true;
				} 
				else 
				if (unicode<48||unicode>57) //if not a number
					return false; //disable key press

			}
		}
		
		function SendMail(form){
			cid=form.orderNo.value;
			window.open("Invoice.do?tabid=ShowEmail&OrderNo="+cid,null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=no,location=no" );
		}
		
		function DeleteRow(d,form){
		response = window.confirm("Do you want to delete this item");
		if(response){	
			size=document.getElementById('CartSize').value;
			
			for(jj=0;jj<size;jj++){
				rowId=document.getElementById(jj+'rowVal').value;
					if(d==rowId){
						document.getElementById(d).style.display='none';
					
						document.getElementById(jj+'delt').value="del";
								
						qty1=document.getElementById(jj+'qty').value;
					
						uprice1=document.getElementById(jj+'uprice').value;
					
						amt = ((qty1)/1 * (uprice1)/1);
					

						wegt=document.getElementById(jj+'weight').value;
						w=document.InvoiceForm.weight.value;
						wg=( (w)/1 - (wegt)/1);
						document.InvoiceForm.weight.value=truncate(wg);
						
						subtotal= document.InvoiceForm.subtotal.value;
					
						subtotal = ((subtotal/1) - (amt/1));
						valu=document.InvoiceForm.taxID.value;
						subtotal=truncate(subtotal);
						document.InvoiceForm.subtotal.value=subtotal;
						sze=document.getElementById("tSize").value;	  						
   						for(i=0;i<sze;i++){
   						var rt=0;
						var field = document.getElementById(i+"tx_id").value;
						if(valu==field){
							rt = document.getElementById(i+"tx_rt").value;
							document.getElementById('tax_field').innerHTML=rt+" %";
							rt = ((((subtotal)/1 ) * (rt/1)) / 100 );
							document.getElementById('tax_val').value=rt;
							break;		
						}
					}
				
					if(rt==0){			
						shipping = document.InvoiceForm.shipping.value;
						total = ( (0) + (subtotal/1) + (shipping/1));
						document.InvoiceForm.total.value=truncate(total);
						document.InvoiceForm.tax.value="0.0";
					}
					else{
						shipping = document.InvoiceForm.shipping.value;
						total = ( (rt/1) + (subtotal/1) + (shipping/1));
						document.InvoiceForm.total.value=truncate(total);
						document.InvoiceForm.tax.value=truncate(rt);
					}
   						
   					
					}
				}
			}
		}
		
		function DeleteRow1(d,form){
		response = window.confirm("Do you want to delete this item");
		if(response){
			document.getElementById('tr'+d).style.display='none';
			for(jj=0;jj<=index1;jj++){
				if(d==jj){
						itemArr[jj]=-1;
						qty1=qtyArr[jj];
		
						uprice1=upriceArr[jj];
		
						qtyArr[jj]=-1;
						upriceArr[jj]=-1;
						codeArr[jj]=-1;
						taxArr[jj]=-1;
						descArr[jj]=-1;
						wegt=uwghtArr[jj];
						uwghtArr[jj]=-1;
						serialArr[jj]=-1;
						itmIDArr[jj]=-1;
						
						amt = ((qty1)/1 * (uprice1)/1);
		

						
						w=document.InvoiceForm.weight.value;
						wg=( (w)/1 - (wegt)/1);
						document.InvoiceForm.weight.value=truncate(wg);
						
						subtotal= document.InvoiceForm.subtotal.value;
					
						subtotal = ((subtotal/1) - (amt/1));
					
						document.InvoiceForm.subtotal.value=subtotal;
						tot=(document.InvoiceForm.shipping.value);
						total = ((tot/1) + (subtotal/1));
						document.InvoiceForm.total.value=total;
						deleted++;
						
					}
				
				}
			}
			
		}
		
		
		 function truncate(num) {
			string = "" + num;
			if (string.indexOf('.') == -1)
				return string + '.00';
			seperation = string.length - string.indexOf('.');
			if (seperation > 3)
				return string.substring(0,string.length-seperation+3);
			else if (seperation == 2)
				return string + '0';
			return string;
		} 
		
		function PrintInvoice(form){
			orderNo=form.orderNo.value;
/*			if(orderNo==""){
				alert("Please select ");
			}*/
			window.open("Invoice.do?tabid=ShowPrint&OrderNo="+orderNo,null,"scrollbars=yes,height=500,width=800,status=yes,toolbar=no,menubar=yes,location=no" );
		}
