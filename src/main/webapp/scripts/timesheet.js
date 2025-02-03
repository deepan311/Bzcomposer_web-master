var ns4 = document.layers? true : false;
var ie = document.all? true : false;
var dom = document.getElementById && !document.all ? true : false;
var temp=null;
var h=null;
var m=-null;
var flag=false;
var date1=new Date();
var date2=new Date();
var date3=new Date();
var date4=new Date();
var tdid="";
var selrow=null;
var mover=null;
var weekhours =new Array(0,0,0,0,0,0,0);
var hdata='<select size="1" id="hour" name="hour"> <option value="00">00</option> <option value="01">01</option> <option value="02">02</option> <option value="03">03</option> <option value="04">04</option> <option value="05">05</option> <option value="06">06</option> <option value="07">07</option> <option value="08">08</option> <option value="09">09</option> <option value="10">10</option> <option value="11">11</option> <option value="12">12</option> <option value="13">13</option> <option value="14">14</option> <option value="15">15</option> <option value="16">16</option> <option value="17">17</option> <option value="18">18</option> <option value="19">19</option> <option value="20">20</option> <option value="21">21</option> <option value="22">22</option> <option value="23">23</option> </select>';
var mdata='<select size="1" id="minute" name="minute" onblur="setValue();"> <option value="00" selected>00</option> <option value="01">01</option> <option value="02">02</option> <option value="03">03</option> <option value="04">04</option> <option value="05">05</option> <option value="06">06</option> <option value="07">07</option> <option value="08">08</option> <option value="09">09</option> <option value="10">10</option> <option value="11">11</option> <option value="12">12</option> <option value="13">13</option> <option value="14">14</option> <option value="15">15</option> <option value="16">16</option> <option value="17">17</option> <option value="18">18</option> <option value="19">19</option> <option value="20">20</option> <option value="21">21</option> <option value="22">22</option> <option value="23">23</option> <option value="24">24</option> <option value="25">25</option> <option value="26">26</option> <option value="27">27</option> <option value="28">28</option> <option value="29">29</option> <option value="30">30</option> <option value="31">31</option> <option value="32">32</option> <option value="33">33</option> <option value="34">34</option> <option value="35">35</option> <option value="36">36</option> <option value="37">37</option> <option value="38">38</option> <option value="39">39</option> <option value="40">40</option> <option value="41">41</option> <option value="42">42</option> <option value="43">43</option> <option value="44">44</option> <option value="45">45</option> <option value="46">46</option> <option value="47">47</option> <option value="48">48</option> <option value="49">49</option> <option value="50">50</option> <option value="51">51</option> <option value="52">52</option> <option value="53">53</option> <option value="54">54</option> <option value="55">55</option> <option value="56">56</option> <option value="57">57</option> <option value="58">58</option> <option value="59">59</option></select>';
function getObject(nameStr) {
    if (dom)
        return document.getElementById(nameStr);
    else if (ie)
        return document.all[nameStr];
    else if (ns4)
        return document.layers[nameStr];
}
function insertTime(val)
{
    debugger;
   if(tdid !="")
   {
     h =document.getElementById("hour").value;
     m=document.getElementById("minute").value;
     document.getElementById(tdid).innerHTML= h+":"+m;
     var r= tdid.charAt(1)*1;
     dailyHours(r);
   }
   
   temp=val;
   var row=temp.charAt(1)*1;
   setRowDates(row);
   var edittd=document.getElementById(val);
   var time=document.getElementById(val).innerHTML;
    
   temp=val;
   edittd.innerHTML = hdata+mdata;
   if(time !="-")
   {
      var arr=time.split(":");
      document.getElementById("hour").value=arr[0];
      document.getElementById("minute").value=arr[1];
    
   }
   tdid=val;
   flag=true;
   document.getElementById("hour").focus();
   
}
function gotoMinute()
{

 h =document.getElementById("hour").value;
 document.getElementById("minute").focus();

}
function setValue()
{
h =document.getElementById("hour").value;
m=document.getElementById("minute").value;





var d1= temp.charAt(1)*1;
var d2= temp.charAt(2)*1;
if(d2==1)
   setTime1(h,m);
if(d2==2)
  setTime2(h,m);   
  
if(d2==3)
  setTime3(h,m);  
if(d2==4)
  setTime4(h,m);  
  
dailyHours(d1);  
 
if(d2<4)
{
  d2=d2+1;
}  
else
{
d2=1;
d1=d1+1;
} 
var param="t"+d1+""+d2;

if(param!="t81")
{
insertTime(param);

}
}

function setRowDates(row)
{
  
  var id="d"+(row-1);
  var rdate=""+document.getElementById(id).innerHTML;
  var arr=rdate.split("-");
  
  date1.setMonth(arr[0]);
  date1.setDate(arr[1]);
  date1.setYear(arr[2]);
 
 
    
   date2.setMonth(arr[0]);
   date2.setDate(arr[1]);
   date2.setYear(arr[2]);
        
   
   date3.setMonth(arr[0]);
   date3.setDate(arr[1]);
   date3.setYear(arr[2]);
   
  
   date4.setMonth(arr[0]);
   date4.setDate(arr[1]);
   date4.setYear(arr[2]);
  
          

 
}
function setTime1(hh,mm)
{
     date1.setHours(hh);
     date1.setMinutes(mm);
     date1.setSeconds(0);
     date1.setMilliseconds(0)


}
function setTime2(hh,mm)
{
     date2.setHours(hh);
     date2.setMinutes(mm);
     date2.setSeconds(0);
     date2.setMilliseconds(0)

}
function setTime3(hh,mm)
{
     date3.setHours(hh);
     date3.setMinutes(mm);
     date3.setSeconds(0);
     date3.setMilliseconds(0)

}
function setTime4(hh,mm)
{
     date4.setHours(hh);
     date4.setMinutes(mm);
     date4.setSeconds(0);
     date4.setMilliseconds(0)

}
function dailyHours(row)
{

var hours1=0;
var hours2=0;
var id="t"+row+"1";
var inhtml1=document.getElementById(id).innerHTML;
id="t"+row+"2";
var inhtml2=document.getElementById(id).innerHTML;
if(inhtml1!=null&& inhtml1!="-" && inhtml2 !=null && inhtml2 !="-")
{

 hours1=date2.getTime()-date1.getTime();

}
if(hours1<0)
hours1=0;

id="t"+row+"3";
var inhtml1=document.getElementById(id).innerHTML;
id="t"+row+"4";
var inhtml2=document.getElementById(id).innerHTML;
if(inhtml1!=null&& inhtml1!="-" && inhtml2 !=null && inhtml2 !="-")
hours2=date4.getTime()-date3.getTime();
if(hours2<0)
hours2=0;

dailytotal=hours1+hours2;
if(dailytotal!=0)
   dailytotal=dailytotal/3600000;
   
id="h"+row;
document.getElementById(id).innerHTML=dailytotal;   
weekhours[row-1]=dailytotal;
var totalhours=0;
for(cnt=0;cnt<7;cnt++)
{
totalhours=totalhours+weekhours[cnt];
}
document.getElementById("WeeklyTotal").innerHTML=""+totalhours;
document.getElementById("TotalHours").innerHTML=""+totalhours +"<br> 0.0  <br> 0.0";

}

function changeColor(val)
{
 
if(selrow != null)
      {
         var temp=document.getElementById(selrow);
          temp.style.background = '#FFFFFF';
          //temp.setAttribute("bgcolor","#FFFFFF");
        
      }
      tdid="";
      selrow=val;
      var sel=document.getElementById(val);
      sel.style.background = '#00ccff';
      //sel.setAttribute("bgcolor","#00ccff");
      document.getElementById("empid").value=val;
      var cells= sel.getElementsByTagName("td");
      var name=cells[1].innerHTML +" "+ cells[2].innerHTML;
      document.getElementById("empname").value=name;
      drawTimesheet();
      setRowData();
      calculateHours();
}


function setRowData()
{
//var rawdata=document.forms[0].tsdata.value;
var rawdata=document.getElementById("tsdata").value;

if(rawdata !=null ||rawdata !="")
{
var rows=rawdata.split("$");
for(cnt=0;cnt<rows.length-1;cnt++)
{
   var cols=rows[cnt].split("#");
   
       var temp=cnt+1; 
       var t= "t"+ temp;
       var t1=t+"1";
       var t2=t+"2";
       var t3=t+"3";
       var t4=t+"4";
       document.getElementById(t1).innerHTML=cols[0];
       document.getElementById(t2).innerHTML=cols[1];
       document.getElementById(t3).innerHTML=cols[2]; 
       document.getElementById(t4).innerHTML=cols[3];
       
}
}
 
}
function calculateHours()
{
 var hours1=0;
 var hours2=0;
 var hm="";
 var inhtml1="";
 var inhtml2="";
 for(cnt=1;cnt<=7;cnt++)
 {
  
   var id="t"+cnt+"1";
   inhtml1=document.getElementById(id).innerHTML;
   id="t"+cnt+"2";
   
   
   inhtml2=document.getElementById(id).innerHTML;
    
   if(inhtml1!=null && inhtml1!="-" && inhtml2 !=null && inhtml2 !="-")
   {
        
        hm=inhtml1.split(":");
        setTime1(hm[0],hm[1]);
        hm=inhtml2.split(":");
        setTime2(hm[0],hm[1]);
        hours1=date2.getTime()-date1.getTime();
   }
   
   if(hours1<0)
   hours1=0;

   id="t"+cnt+"3";
   inhtml1=document.getElementById(id).innerHTML;
   id="t"+cnt+"4";
   inhtml2=document.getElementById(id).innerHTML;
   if(inhtml1!=null&& inhtml1!="-" && inhtml2 !=null && inhtml2 !="-")
   {
         
           hm=inhtml1.split(":");
           setTime3(hm[0],hm[1]);
           hm=inhtml2.split(":");
           setTime4(hm[0],hm[1]);
           hours2=date4.getTime()-date3.getTime();
           
           
   }        
   if(hours2<0)
   hours2=0;
 
   dailytotal=hours1+hours2;
   if(dailytotal!=0)
       dailytotal=dailytotal/3600000;
   
    id="h"+cnt;
    document.getElementById(id).innerHTML=dailytotal;   
    weekhours[cnt-1]=dailytotal;
   
    hours1=0;
    hours2=0;
 
 }
 var totalhours=0;
 for(cnt=0;cnt<7;cnt++)
 {
   totalhours=totalhours+weekhours[cnt];
 }
   document.getElementById("WeeklyTotal").innerHTML=""+totalhours;
   document.getElementById("TotalHours").innerHTML=""+totalhours +"<br> 0.0  <br> 0.0";
}


function rowClick(val)
{    
     if(curr != null)
      {
         var temp=document.getElementById(curr);
         //temp.setAttribute("bgcolor","#FFFFFF");
         temp.style.background="#FFFFFF";
           
      }
      curr=val;
      var sel=document.getElementById(val);
      sel.style.background="#00ccff";
    
  
}
function sendTo()
{
   var sel=document.getElementById(curr);
   var cells= sel.getElementsByTagName("td");
   var name=cells[1].innerHTML +" "+ cells[2].innerHTML;
   var op=document.createElement('OPTION');
   var txt=document.createTextNode(name);
   op.setAttribute('value',name);
   op.appendChild(txt);
   document.getElementById("list").appendChild(op);
}

function clearData()
{
	 var list=document.getElementById("list");
	 var ops=list.getElementsByTagName('OPTION');
	 
	 list.options.length=0;
	 //var len=list.length;
	 //if(len>0)
	 //{
	//	 for(i=0;i<len;i++)
	//	 {
	 //  		  list.options[i]=null;
			  //list.removeChild(ops[i]);
	//	 }
	 //}
}