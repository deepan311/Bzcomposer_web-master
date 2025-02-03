//Author: Sarfraz-Malik
let selectValidCountryMsg = "";
let selectValidStateMsg = "";
let selectValidZipcodeMsg = "";
let noRecordsFoundMsg = "";
function testMessage(){
    alert("This is test message fom myAppUtility.js");
}
function validateUSAPhoneNumber(thisItem, isCopyCountryCode){
    let newNumber = "";
    let myNumber = thisItem.value;
    let brackIndex = myNumber.indexOf('(');
    if(brackIndex>0){
        myNumber = myNumber.substring(brackIndex);
    }
    myNumber = myNumber.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
    if(myNumber.length<3){
        newNumber = "("+myNumber;
    }
    else if(myNumber.length<6){
        newNumber = "("+myNumber.substring(0,3)+") "+myNumber.substring(3);
    }else{
        newNumber = "("+myNumber.substring(0,3)+") "+myNumber.substring(3,6)+"-"+myNumber.substring(6);
    }
    if(isCopyCountryCode){
        let countryCode = $("#countryID").find("option:selected").data("code");
        newNumber = countryCode + newNumber;
    }
    //if($('#countryID').val() == 231 || $('#countryID').val() == 2){
        thisItem.value = newNumber;
    //}
}
function copyPhoneNumber(form){
    if(form.isMobilePhoneNumber.checked){
        form.cellPhone.value = form.phone.value
    }
}

function loadStatesByCountryID(countryId, indexNo){
    indexNo = indexNo==1?"":indexNo;
    if($("#countryID"+indexNo).find("option:selected").text().trim() == 'Canada'){
        $('.lblProvinceShow').show();
        $('.lblPostalcodeShow').show();
        $('.lblStateShow').hide();
        $('.lblZipcodeShow').hide();
    }else{
        $('.lblProvinceShow').hide();
        $('.lblPostalcodeShow').hide();
        $('.lblStateShow').show();
        $('.lblZipcodeShow').show();
    }
    if(countryId == 0){
        alert(selectValidCountryMsg);
    }
    else{
        $.ajax({
            type: "GET",
            url:"/RegisterAPI?tabid=loadStatesByCountryID&id="+countryId,
            success:function(data){
                let stateDropdown = $("#stateID"+indexNo), option = "";
                stateDropdown.empty();
                for(var i=0;i<data.length;i++){
                    option = option+"<option value='"+data[i].stateId+"'>"+data[i].state+"</option>"
                }
                stateDropdown.append(option);
                stateDropdown.val(data[0].stateId).change();
            },
            error:function(){
                alert("error");
            }
        });
    }
}

function loadCitiesByStateID(stateId, indexNo){
    indexNo = indexNo==1?"":indexNo;
    if(stateId == 0){
        alert(selectValidStateMsg);
    }
    else{
        $.ajax({
            type: "GET",
            url:"/RegisterAPI?tabid=loadCitiesByStateID&id="+stateId,
            success:function(data){
                let cityDropdown = $("#cityID"+indexNo), option = "";
                cityDropdown.empty();
                for(var i=0;i<data.length;i++){
                    option = option+"<option value='"+data[i].cityId+"'>"+data[i].cityName+"</option>"
                }
                cityDropdown.append(option);
            },
            error:function(){
                alert("error");
            }
        });
    }
}

function loadAddressDetailsByZipcode(zipCode, indexNo){
    indexNo = indexNo==1?"":indexNo;
    if(zipCode.length < 4 || zipCode.length > 6){
        alert(selectValidZipcodeMsg);
    }
    else{
        $.ajax({
            type: "POST",
            url:"/RegisterAPI?tabid=loadAddressDetailsByZipcode&zipcode="+zipCode,
            data :"zipCode=" + zipCode,
            success : function(data) {
                if(data){
                    $("#countryID"+indexNo).val(data.country).change();
                    setTimeout(function(){
                        $("#stateID"+indexNo).val(data.state).change();
                        setTimeout(function(){
                            $("#cityID"+indexNo).val(data.cityID);
                        }, 1000);
                    }, 1000);
                }else{
                    alert(noRecordsFoundMsg);
                }
            },
            error : function(data) {
                alert('ERROR');
            }
        });
    }
}

function numbersonly(e, val){
	var unicode = e.charCode? e.charCode : e.keyCode;
	if (unicode!=8){
 		if (unicode<48||unicode>57){
			return false; //disable key press
		}
	}
}
function numbersOnlyFloat(e, val) {
	var temp=val.indexOf(".");
	var unicode=e.charCode? e.charCode : e.keyCode;
	if (unicode!=8){
 		//if the key isn't the backspace key (which we should allow)
		if(unicode==46 && temp==-1) {
 			return true;
		} else {
			if (unicode<48||unicode>57){ //if not a number
				return false; //disable key press
			}
	    }
	}
}