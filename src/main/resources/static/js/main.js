
document.getElementById("nameClient").style.display="none";
document.getElementById("emailClient").style.display="none";
function myFunction(){

    var forVirement = document.getElementById("forVirement").style.display="block";
}

function myFunction2(){

    var forVirement = document.getElementById("forVirement").style.display="none";
}


function functionTaux(){

    console.log("taux")
    var forVirement = document.getElementById("taux").style.display="block";
    var forVirement = document.getElementById("decouvert").style.display="none";
}

function  functionDecouvert(){

    console.log("decouvert")
    var forVirement = document.getElementById("taux").style.display="none";
    var forVirement = document.getElementById("decouvert").style.display="block";
}
function showClientDetail(){


    var forVirement = document.getElementById("nameClient").style.display="block";
    var forVirement = document.getElementById("emailClient").style.display="block";
}

