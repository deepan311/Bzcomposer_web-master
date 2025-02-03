/*esales board menu start*/
var globalSelectedPreferenceType = null;
$(document).ready(function(){
	
	$(".esb_tab1_details").show();
	$(".esb_tab2_details").hide();
	$(".esb_tab2_details_inner_tab2_details").hide();
	
    $("#esb_tab1").click(function(){
        $(".esb_tab1_details").show();
        $(".esb_tab2_details").hide();
    });
    $("#esb_tab2").click(function(){
    	$(".esb_tab2_details").show();
        $(".esb_tab1_details").hide();
    });
    
    /**/
    $("#esb_tab2_details_inner_tab1").click(function(){
        $(".esb_tab2_details_inner_tab1_details").show();
        $(".esb_tab2_details_inner_tab2_details").hide();
    });
    $("#esb_tab2_details_inner_tab2").click(function(){
    	$(".esb_tab2_details_inner_tab2_details").show();
        $(".esb_tab2_details_inner_tab1_details").hide();
    });
    

    $('.add').on('click', function() {
		var options = $('select.multiselect1 option:selected').sort().clone();
	    var selectedCountry =$('select.multiselect1 option:selected').val();
	    var x = document.getElementById("mySelect2");
	    var txt="All Options";
	    var i;
	    for (i = 0; i < x.length; i++) {
	        txt = txt + "\n" + x.options[i].text;
	    }
	    alert(txt);
	    alert(selectedCountry);
	    
	    $('select.multiselect2').append(options);
	    
	    /*for (i = 0; i < x.length; i++)
	    {
	       if(txt==selectedCountry)
	    	   {
	    	   	alert("sorry");
	    	   }
	       else
	    	   {
	    	   		$('select.multiselect2').append(options);
	    	   }
	    }*/
	    
	    
	});
    
    $('.remove').on('click', function() {
        $('select.multiselect2 option:selected').remove();
    });
    
    
});

/*esales board menu over*/



$(document).ready(function(){

	$(".mpw_tab1_details").show();
	$(".mpw_tab2_details").hide();
	//$(".esb_tab2_details_inner_tab2_details").hide();
	
	$("#mpw_tab1").click(function(){
		debugger;
        $(".mpw_tab1_details").show();
        $(".mpw_tab2_details").hide();
    });
	
	
	$("#mpw_tab2").click(function(){
		debugger;
        $(".mpw_tab1_details").hide();
        $(".mpw_tab2_details").show();
    });
	
});



/*select feature on create new company setup*/
$(document).ready(function(){
	
	var item = [];
	var options2 = $('select.featureName1 option:selected').sort().clone();
	
$('.addfeature').on('click', function() {
	var options1 = $('select.featureName1 option:selected').sort().clone();
    var selectedfeatures =$('select.featureName1 option:selected').val(); 
    debugger;
    if(selectedfeatures == undefined){
    	alert("Please select any value first");
    } else {
        $('select.featureName2').append(options1);
        //$("select.featureName2 option").prop("selected",true);
        var selectedfeatures2 =$('select.featureName2 option:selected').val();

    		/*item.push(selectedfeatures2);
    		localStorage.setItem("testKey", JSON.stringify(item));*/
    		
    		/*var test = JSON.parse(localStorage.getItem("testKey"));
    		alert(test);*/
    }
});

/**/
//var test = JSON.parse(localStorage.getItem("testKey"));
$('select.featureName2').append(options2);
//$("select.featureName2 option").prop("selected",true);    			//Commented on 29-04-2019

/**/

$('.removefeature').on('click', function() {
    $('select.featureName2 option:selected').remove();				
    var options1 = $('select.featureName2 option:selected').sort().clone();
    var selectedfeatures =$('select.featureName2 option:selected').val();
    debugger;
    $('select.featureName1').append(options1);
    //$("select.featureName2 option").prop("selected",true);
    //$("select.featureName1 option").prop("selected",false);
});

});

/**/


function vAcctCategory()
{
	var items = document.querySelectorAll("#testul li");
	var objSelect = document.getElementById("selecttest");
	
	debugger;
	
	var selectedValue = null;
	
	for(var i=0; i < items.length; i++)
		{
		
			items[i].onclick = function(){
				
				//document.getElementById("selecttest").value = this.innerHTML;
				debugger;
				selectedValue = this.innerHTML;
				setSelectedValue(objSelect, selectedValue);
				
			};
			
			
			function setSelectedValue(selectObj, valueToSet)
			{
			    for (var i = 0; i < selectObj.options.length; i++)
			    {
			        if (selectObj.options[i].text== valueToSet)
			        {
			            selectObj.options[i].selected = true;
			            return;
			        }
			    }
			}
				
		}
}

/*$("#add").click(function(){
	debugger;
    $("#testul").append("<li>Appended item</li>");
});*/



function add()
{
	var list = $("#selecttest option:selected").val();
	var items = document.querySelectorAll("#myUL li");
	var value = $("#select_option option:selected").val();
	//$('#bca_add').text();
	var livalue=null;
	
	if($('#bca_add').text() == 'Edit')
		{
			var candidate = document.getElementById("accname");
			$('.bca_bankaccountlist #myUL .caret #nested li').each(function(i)
					{
				debugger;
				
			 livalue = $(this).attr('id');
				
					});
			
			var s = $("#"+livalue).text(candidate.value);
			
		}
	else
		{
		
		debugger;
		
		var selectedValue = list;
		
		$('#myUL li').each(function(i)
		{
			
			debugger;
			var livalue = $(this).attr('id');
			
			debugger;
			if(livalue == selectedValue)
				{
				debugger;
					
					var ul = $("#myUL #"+livalue+" #nested")[0];
					var candidate = document.getElementById("accname");
				    var li = document.createElement("li");
				    li.setAttribute('id',candidate.value);
				    li.setAttribute('class',"jay");
				    li.appendChild(document.createTextNode(candidate.value));	
				    ul.appendChild(li);
				    $(".nested").show();
				  //  this.classList.show("caret-down");
				   // this.classList.show("caret::before");
					
				    
			    
				}
			/*else
				{
				debugger;
					alert("false");
				}*/
			
			
		});
		}
	
	
	
	$('#accname').val("");
	$('#bca_add').text('Add');
	

	

}



$(document).on('click', '.bca_bankaccountlist #myUL .caret #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_bankaccountlist #myUL .caret #nested li");
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("accname").value = this.innerHTML;
			
		};
	}
	
	$('#bca_add').text('Edit');
	
	
	
	
});

/*screen 5*/
/*$(document).on('click', '.bca_setuppreference_add', function(){
	
	debugger;
	var items = document.querySelectorAll("#myUL li");
	//var terms = document.getElementById("#term_");
	
	var textid = null;
	
	
	textid = $("#caret").attr('id');
	
	
	$('#myUL li').each(function(i)
			{
		
		debugger;
		
			var livalue = $(this).attr('class');
			
			debugger;
			if(livalue == textid)
				{
				debugger;
					
					var ul = $("#myUL ."+livalue+" #nested")[0];
					var settextboxid = document.getElementById("term_day");
					var settextboxvalue = document.getElementById("caret");
				    var li = document.createElement("li");
				    li.setAttribute('id',settextboxid.value);
				    li.appendChild(document.createTextNode(settextboxvalue.value));	
				    ul.appendChild(li);
				    $(".nested").show();
				  //  this.classList.show("caret-down");
				   // this.classList.show("caret::before");
				    settextboxvalue.value = "";
				    settextboxid.value = "";
				}
	
		
			});
	
});*/

$(document).on('click', '.bca_preferencelist #myUL .caret #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret #nested li");
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			debugger;
			document.getElementById("caret").value = this.innerHTML;
			document.getElementById("term_day").value = $(this).attr('id');
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
	
	
});

$(document).on('click', '.bca_preferencelist #myUL .caret2 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret2 #nested li");
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("salerep_").value = this.innerHTML;
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
	
});

$(document).on('click', '.bca_preferencelist #myUL .caret3 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret3 #nested li");
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("itemcate_").value = this.innerHTML;
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
	
});

$(document).on('click', '.bca_preferencelist #myUL .caret4 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret4 #nested li");
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("creditcardtype_").value = this.innerHTML;
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
	
});



$(document).on('click', '.bca_preferencelist #myUL .caret5 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret5 #nested li");
		
	var select = document.getElementById("initPaymentType").value ;
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("initPaymentType_").value = this.innerHTML;
			
			var clickedSubMenu = $(this);
		    $('#initPaymentType option:selected').html(clickedSubMenu.text());
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
});


$(document).on('click', '.bca_preferencelist #myUL .caret6 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret6 #nested li");
		
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("initShipCarrier_").value = this.innerHTML;
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
});


$(document).on('click', '.bca_preferencelist #myUL .caret7 #nested', function(){
	
	
	var items = document.querySelectorAll(".bca_preferencelist #myUL .caret7 #nested li");
		
	var select = document.getElementById("initReceivedType").value ;
	
	
	for(var i=0; i < items.length; i++)
	{
		items[i].onclick = function(){
			
			document.getElementById("initReceivedType_").value = this.innerHTML;
			
			var clickedSubMenu = $(this);
		    $('#initReceivedType option:selected').html(clickedSubMenu.text());
			
		};
	}
	
	/*$('.bca_setuppreference_add').text('Edit');*/
	$('#add').replaceWith('<li class=bca_setuppreference_add id=edit><< Edit</li>');
});

/**/
/*changed by pritesh 26-11-2018*/
$(document).on('click','.caret',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").show();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret2',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").show();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret3',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;
	/*	alert(globalSelectedPreferenceType);*/
		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").show();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret4',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").show();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret5',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").show();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret6',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").show();
		$("#bca_initReceivedType").hide();
	}	
});
$(document).on('click','.caret7',function(e)
		{
	if(e.target.id == this.id)
	{
		globalSelectedPreferenceType = this.id;

		$('#edit').replaceWith('<li class=bca_setuppreference_add id=add><< Add</li>');
		$("#bca_initTerm").hide();
		$("#bca_initSalesRep").hide();
		$("#bca_initItenCategory").hide();
		$("#bca_initCreditCardType").hide();
		$("#bca_initPaymentType").hide();
		$("#bca_initShipCarrier").hide();
		$("#bca_initReceivedType").show();
	}	
});

$(document).on('click','#add',function(){
	debugger;

	if(globalSelectedPreferenceType != null)
	{	
		if($("#caret").val() != '' && $("#caret").val() != null)
		{	
			$("#"+globalSelectedPreferenceType+" ul").append('<li>' +$("#caret").val()+'</li>');
		}	
		else{
			$("#"+globalSelectedPreferenceType+" ul").append('<li>' +$("#itemcate_").val()+'</li>');
			alert("Please input a name first");
		}
	}	
	else{
		alert("Please select a preference type first");
	}
	globalSelectedPreferenceType = null;
});
$(document).on('click','#edit',function(e){
	debugger;
	alert(this.id);
	
});
function preference1()
{
	debugger;
	var ul = $("#myUL #"+1+" #nested");
	var candidate = document.getElementById("term_");
    var li = document.createElement("li");
    li.setAttribute('id',candidate.value);
    li.appendChild(document.createTextNode(candidate.value));	
    ul.appendChild(li);
}
















  
