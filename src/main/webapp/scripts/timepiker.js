
var newDate;
// Object created
var TimeSheetForm = {};
let startVal1;
let finishVal1;
let startVal2;
let finishVal2;
$(document).ready(function(){
    $(window).load(function(){
        populateDates();

    });
    const weekDays = ['Sunday','Monday','Tuesday','Wednesday','Thursday','Friday','Saturday'];
    $('#datePicker').datepicker({ //initiate JQueryUI datepicker
        showAnim: 'fadeIn',
        dateFormat: "dd/mm/yy",
        todayHighlight: true,
        firstDay: 1, //first day is Monday
        onSelect: populateDates
    });

    function getMonday(d) {
      d = new Date(d);
      var day = d.getDay(),
          diff = d.getDate() - day + (day == 0 ? -6:1); // adjust when day is sunday
      return new Date(d.setDate(diff));
    }
    function populateDates() {
        var empid = document.getElementById("employeeid").value;

        $('#tBody').empty(); //clear table
        $('.bottom').removeClass('d-none'); //display total hours worked
        let chosenDate = $('#datePicker').datepicker('getDate'); //get chosen date from datepicker
        // let chosenDate = $('#datePicker').datepicker('getDate'); //get chosen date from datepicker

        chosenDate = getMonday(new Date());
        const monStartWeekDays = ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'];
        for(let i = 0; i < weekDays.length; i++) { //iterate through each weekday
            newDate = new Date(chosenDate); //create date object
            newDate.setDate(chosenDate.getDate() + i); //increment set date
            let date11 = newDate.getDate();
            if(date11.toString().length == 1){

                date11 = "0"+date11;
            }
            let month11 = newDate.getMonth() + 1;
            if(month11.toString().length == 1){

                month11 = "0"+month11;
            }
            //append results to table
            $('#tBody').append( `
          <tr>
            <td class="day">${weekDays[newDate.getDay()].slice(0,3)}</td>
            <td class="date">${newDate.getFullYear()}-${month11}-${date11}</td>
            <input id="timerdate${monStartWeekDays[i]}" class="time ui-timepicker-input" type="hidden" value="${newDate.getFullYear()}-${month11}-${date11}" />
            <td class="start-time1"><input id="startTime1${monStartWeekDays[i]}" class="time ui-timepicker-input" type="text" /></td>
            <td class="finish-time1"><input id="finishTime1${monStartWeekDays[i]}" class="time ui-timepicker-input" type="text" /></td></td>
            <td class="start-time2"><input id="startTime2${monStartWeekDays[i]}" class="time ui-timepicker-input" type="text" /></td>
            <td class="finish-time2"><input id="finishTime2${monStartWeekDays[i]}" class="time ui-timepicker-input" type="text" /></td></td>
            <td class="break">
            <select id="break${monStartWeekDays[i]}">
                <option value="0">0 h</option>
                <option value="0.5">0.5 h</option>
                <option value="1">1 h</option>
              </select>
             
            </td>
            <td class="hours-worked" id="hoursWorked${monStartWeekDays[i]}">
              0
            </td>
            <td></td>
          </tr>
          ` );
            //function to calculate hours worked
            let calculateHours = () => {
                let timerdate = $(`#timerdate${monStartWeekDays[i]}`).val();
                startVal1 = $(`#startTime1${monStartWeekDays[i]}`).val();
                finishVal1 = $(`#finishTime1${monStartWeekDays[i]}`).val();
                startVal2 = $(`#startTime2${monStartWeekDays[i]}`).val();
                finishVal2 = $(`#finishTime2${monStartWeekDays[i]}`).val();

                let startTime1 = new Date( `01/01/2007 ${startVal1}` );
                let finishTime1 = new Date( `01/01/2007 ${finishVal1}` );
                let startTime2 = new Date( `01/01/2007 ${startVal2}` );
                let finishTime2 = new Date( `01/01/2007 ${finishVal2}` );


                let breakTime = $(`#break${monStartWeekDays[i]}`).val();
                let hoursWorked = ((finishTime1.getTime() - startTime1.getTime()) / 1000);

                if (startVal2 && finishVal2) {
                    hoursWorked = hoursWorked + ((finishTime2.getTime() - startTime2.getTime()) / 1000)
                }
                hoursWorked /= (60 * 60);
                hoursWorked -= breakTime;
                //set data in object
                TimeSheetForm[timerdate] = {
                    "date" : timerdate+" 00:00:00",
                    "Start Work 1" : timerdate+" "+startVal1+":00",
                    "End Work 1" : timerdate+" "+finishVal1+":00",
                    "Start Work 2" : timerdate+" "+startVal2+":00",
                    "End Work 2" : timerdate+" "+finishVal2+":00",
                    "Break" : breakTime,
                    "hoursWorked":hoursWorked+""
                }
                if (startVal1 && finishVal1) { //providing both start and finish times are set
                    if (hoursWorked >= 0) { //if normal day shift
                        $(`#hoursWorked${monStartWeekDays[i]}`).html(hoursWorked);
                    } else { //if night shift
                        $(`#hoursWorked${monStartWeekDays[i]}`).html(24 + hoursWorked);
                    }
                }
                updateTotal();
            }
            //initiate function whenever an input value is changed
            $(`#startTime1${monStartWeekDays[i]}, #finishTime1${monStartWeekDays[i]},#startTime2${monStartWeekDays[i]}, #finishTime2${monStartWeekDays[i]}, #break${monStartWeekDays[i]}`).on('change', calculateHours);

        }
        $('.start-time1 input').timepicker({ 'timeFormat': 'H:i', 'step': 15, 'scrollDefault': '09:00' });
        $('.finish-time1 input').timepicker({ 'timeFormat': 'H:i', 'step': 15, 'scrollDefault': '17:00' });
        $('.start-time2 input').timepicker({ 'timeFormat': 'H:i', 'step': 15, 'scrollDefault': '09:00' });
        $('.finish-time2 input').timepicker({ 'timeFormat': 'H:i', 'step': 15, 'scrollDefault': '17:00' });

        function updateTotal() { //function to update the total hours worked
            let totalHoursWorked = 0;
            let hrs = document.querySelectorAll('.hours-worked');
            hrs.forEach(function(val) {
                totalHoursWorked += Number(val.innerHTML);
            });
            document.querySelector('#totalHours').innerHTML = totalHoursWorked;

        }
        GetTimeSheetData(empid);
    }
});

function SetEmpId(){

    var empid = document.getElementById("employeeid").value;
    TimeSheetForm["employeeid"] = {
        "empid" : empid
    }
    clearData();
    GetTimeSheetData(empid);


}

