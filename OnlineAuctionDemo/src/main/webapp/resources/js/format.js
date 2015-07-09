function checkForm() {
	  username=validusername();
	  fullname=validfullname();
	  password=validpassword();
	  repassword=validrepassword();
	  email=validemail();
	  address=validaddress();
	  samepass=validsamepass();

	  return username && fullname && password && repassword && email && address && samepass;
		
		}
function samepassword(password,repassword,print){
	var pass=document.getElementById(password).value.trim();
	var repass=document.getElementById(repassword).value.trim();
	if(pass==repass){
		
	document.getElementById(print).innerHTML="";
	document.getElementById(password).value=pass;
	document.getElementById(repassword).value=repass;
	return true;}
	document.getElementById(print).innerHTML="Password khong khop nhau!";
    return false;
}
function validate(ipname,nameer,ml,mxl){
	var name=document.getElementById(ipname).value.trim();
	if(name.length>=ml && name.length<=mxl){
		var regex=/^[a-zA-Z0-9]+$/;
		if (!regex.test(name)){
			document.getElementById(nameer).innerHTML=ipname+" chi gom cac chu cai a-z, A-Z va chu so 0-9";
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
function validateemail(email,femail){
	        var name=document.getElementById(email).value.trim();
			var regexp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}$/;
			if(name==""){
				document.getElementById(femail).innerHTML="Email Khong duoc rong!";
				return false;
			   }
			else
		if (!regexp.test(name)){
			document.getElementById(femail).innerHTML="Email khong hop le!";
			return false;
		}
		else{
		document.getElementById(femail).innerHTML="";
		document.getElementById(email).value=name;
		return true;}
	   
	
}
function validusername(){
	return validate("Username","fusername",2,15);
}
function validfullname(){
	return validate2("Fullname","ffullname",4,50);
}
function validpassword(){
	return validate("Password","fpassword",6,15);
}
function validrepassword(){
	return validate("Repassword","frepassword",6,15);
}
function validaddress(){
	return validate2("Address","faddress",3,100);
}
function validemail(){
	return validateemail("Email","femail");
}
function validsamepass(){
	return samepassword("Password","Repassword", "fpassword");
	
}