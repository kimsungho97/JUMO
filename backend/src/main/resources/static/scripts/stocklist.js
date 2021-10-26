function searchByCode(){
    let tableRows=document.querySelectorAll(".stock-table-row");

    tableRows.forEach((value)=>{
        if(value.children[1].innerHTML.indexOf(this.value)===-1){
            value.style.display="none";
        }
        else{
            value.style.display="table-row";
        }
    })
}


function searchByName(){
    let tableRows=document.querySelectorAll(".stock-table-row");

    tableRows.forEach((value)=>{
        if(value.children[0].innerHTML.indexOf(this.value)===-1){
               value.style.display="none";
        }
        else{
            value.style.display="table-row";
        }
    })
}


const searchCode=document.querySelector(".search-code")
const searchName=document.querySelector(".search-name")

searchCode.addEventListener("keyup",searchByCode)
searchName.addEventListener("keyup",searchByName)