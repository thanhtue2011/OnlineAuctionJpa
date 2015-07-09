function checkForm() {
	  title=validtitle();
	  mota=validmota();
	  dongia=validdongia();
	  datestart=validdatestart();
	  dateend=validdateend();
	
	  samedate=validsame();

	  return title && mota && dongia;
		
		}
function samedate(datestart,dateend,print){
	var start=document.getElementById(datestart).value.trim();
	var end=document.getElementById(dateend).value.trim();
	if(start<end){
		
	document.getElementById(print).innerHTML="";
	document.getElementById(datestart).value=start;
	document.getElementById(dateend).value=end;
	return true;}
	document.getElementById(print).innerHTML="Date end phai lon hon Date start!";
    return false;
}

function validate2(ipname,nameer,ml,mxl){
	var name=document.getElementById(ipname).value.trim();
	if(name.length>=ml && name.length<=mxl){
		var regex=/^[a-zA-Z0-9 ]+$/;
		if (!regex.test(name)){
			document.getElementById(nameer).innerHTML=ipname+" khong hop le!";
			return false;
		}
		document.getElementById(nameer).innerHTML="";
		document.getElementById(ipname).value=name;
		return true;
	}
	else if(name==""){
		document.getElementById(nameer).innerHTML=ipname+" khong duoc rong!";
		return false;
	}
	else{
		document.getElementById(nameer).innerHTML=ipname+" phai co do dai "+ ml + " den "+ mxl + " ky tu";
		return false;
	}
}
function validate3(ipname,nameer){
	var name=document.getElementById(ipname).value.trim();
	var regex=/^[0-9]+$/;
	 if(name==""){
			document.getElementById(nameer).innerHTML=ipname+" khong duoc rong!";
			return false;
		}
	 else
		if (!regex.test(name)){
			document.getElementById(nameer).innerHTML=ipname+" khong hop le!";
			return false;
		}
		else
			if(name>=50000){
		document.getElementById(nameer).innerHTML="";
		document.getElementById(ipname).value=name;
		return true;
	    }
	
	    else{
		document.getElementById(nameer).innerHTML=ipname+" phai lon hon 50000 tro len!";
		return false;
	}
}
function validdate(idate,fdate){
	var date=document.getElementById(idate).value.trim();
	if(date.length>0){
		var rg1 = /^(0?[1-9]|[12]\d|30)\/(0?[13-9]|1[0-2])\/((19|20)\d\d)$/;
		var rg2 = /^(31)\/(0?[13578]|1[02])\/((19|20)\d\d)$/;
		var rg3 = /^(0?[1-9]|1\d|2[0-8])\/(02|2)\/((19|20)\d\d)$/;
		var rg4 = /^(29)\/(02|2)\/(((19|20)(0[48]|[13579][26]|[2468][480]))|2000)$/;
		if (rg1.test(date) || rg2.test(date) || rg3.test(date) || rg4.test(date)){
			document.getElementById(fdate).innerHTML="";
			document.getElementById(idate).value=date;
			return true;
			
		}
		document.getElementById(fdate).innerHTML=idate+" Dinh dang ngay phai la dd/MM/yyyy!";
		return false;
	}
	else {
		document.getElementById(fdate).innerHTML=idate+" Khong duoc rong!";
		return false;
	}
}

function validdatestart(){
	return validate("DateStart","fdatestart");
} 
function validdateend(){
	return validate("DateEnd","fdateend");
}
function validtitle(){
	return validate2("Title","ftitle",1,100);
}
function validmota(){
	return validate2("Descreption","fmota",10,4000);
}
function validdongia(){
	return validate3("dongia","fdongia");
}
function validdatestart(){
	
	return validdate("DateStart","fdatestart");
}
function validdateend(){
	
	return validdate("DateEnd","fdateend");
}
function validsame(){
	
	return samedate("DateStart","DateEnd","fdatestart");
}
