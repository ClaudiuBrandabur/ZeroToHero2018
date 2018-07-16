var employeesList = [
    {
        firstName: 'John',
        lastName: 'King',
        phone: '0123456789',
        salary:'4500'
    },

    {
        firstName: 'Steven',
        lastName: 'Gerald',
        phone: '0123456789',
        salary:'4500'
    },

    {
        firstName: 'Diana',
        lastName: 'Ross',
        phone: '0123456789',
        salary:'4500'
    },

    {
        firstName: 'Mike',
        lastName: 'Bob',
        phone: '0123456789',
        salary:'4500'
    },

    {
        firstName: 'Emily',
        lastName: 'Hudson',
        phone: '0123456789',
        salary:'4500'
    }
];

function showList() {

    var myTable = '<table border="1"><tr><th>First Name</th><th>Last Name</th><th>Phone</th>' +
        '<th>Salary <button onclick="convertSalary()">Convert</button> </th>  ' +
        '<th>Vizualizare </th> ' +
        '<th>Stergere</th></tr>';



    for (var i in employeesList) {
        myTable += '<tr><td>' + employeesList[i].firstName + '</td><td>' + employeesList[i].lastName + '</td><td>' +
            employeesList[i].phone + '</td><td>' + employeesList[i].salary + '</td> <td><button onclick="vizualizare('+i+')">Vizualizare</button> </td> <td><button onclick="stergere('+i+')">Stergere</button> </td> </tr>';
    }

    myTable += '<tr><td>'+cmd_aparitie()+
        '</td><td>'+nr_unique_names()+
        '</td><td>'+nrfrequency()+
        '</td><td>'+average()+
        '</td></tr>';
    myTable += '</table>';

    var container = document.getElementById('listcontainer');
    container.innerHTML = myTable;
}


    var Employee = function (firstName, lastName, phone, salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.salary =salary;

    };

    function addEmployee(){
        var firstName = document.getElementById("firstName").value;
        var lastName = document.getElementById("lastName").value;
        var phone = document.getElementById("phone").value;
        var salary = document.getElementById("salary").value;
        employeesList.push(new Employee(firstName,lastName,phone,salary));
        showList();
    }

    function sumList() {

        var mySum = 0;
        for(var i in employeesList){
            mySum += parseInt(employeesList[i].salary.toString());
        }

        var bttn = document.getElementById('sum');
        bttn.innerHTML = mySum;
    }

    function delEmployee() {
        employeesList.pop();
        showList();
    }

    function convertSalary(){

        var tb = document.getElementById('listcontainer');

        // for(var i in employeesList){
        //   employeesList[i].prototype.convert = employeesList[i].salary / 4.5;
        //}
        var i = 0;
        var nw = 0;

        for(i =0;i < employeesList.length;i++){
            //tb[3].cellIndex
            var newTH = document.createElement('th')
            tb.rows[i].appendChild(newTH);
            newTH.innerHTML = "C" + (tb.rows[i].cells.length);

           // nw = tr[i].insertCell(3);
           // nw.document.getElementsByTagName("td").value = parseFloat(employeesList[i].salary) / 4.5;
            //nw.innerHTML = employeesList[i].convert;
           // nw = tb[i].insertCell(-1);
           // nw.getElementById('listcontainer').value = parseFloat(employeesList[i].salary) / 4.5;
           // employeesList[i].prototype.convert = parseFloat(employeesList[i].salary) / 4.5;
           // nw.innerHTML = employeesList[i].convert;

        }

        for (var i=0; i< tb.rows.length; i++) {

            var newCell = tb.rows[i].insertCell(-1); //create new cell

            newCell.innerHTML = 'cell '+ (tb.rows[i].cells.length); //append data to cell
        }
    }

    function vizualizare(vb) {
        alert(employeesList[vb].firstName +" "+ employeesList[vb].lastName +" "+ employeesList[vb].phone +" "+ employeesList[vb].salary);
    }


    function stergere(vb){

        employeesList.splice(vb,1);
        showList();
    }

    function cmd_aparitie() {
        employeesList.sort(compareFirst);
        var index = 0;
        var nr = 0;
        var nr_max = 0;
        var i;
        for(i = 0;i < employeesList.length-1; i++){
            if(employeesList[i].firstName === employeesList[i+1].firstName){
                nr++;
            }else{
                if(nr > nr_max){
                    nr_max = nr;
                    index = i;
                }
                nr = 0;
            }
        }
        return employeesList[index].firstName;
    }

    function compareFirst(a,b) {
        if(a.firstName < b.firstName)
            return -1;
        if(a.firstName > b.firstName)
            return 1;


    }

    function compareLast(a,b) {
        if(a.lastName < b.lastName)
            return -1;
        if(a.lastName > b.lastName)
            return 1;


}



    function nr_app(nr) {

        var i;
        var aparitii = 0;
        for(i in employeesList){
            if(employeesList[i].lastName === nr){
                aparitii++;
            }
        }
        return aparitii;
    }
    function nr_unique_names() {

        var i;
        var nr = 0;
        var myList = [];

        for(i in employeesList){
            if(nr_app(employeesList[i].lastName)===1){
                if(! myList.includes(employeesList[i].lastName)){
                    nr++;
                    myList.push(employeesList[i].lastName);

                }
            }
        }
        return nr;
    }

    function numberTel(x) {
        var i = 0;
        var vect;

        vect = [0,0,0,0,0,0,0,0,0,0];

        for(i = 0; i < x.length; i++){
            vect[parseInt(x.charAt(i))] += 1;
        }

        return vect;
    }

    function nrfrequency() {

        var vect;
        vect = [0,0,0,0,0,0,0,0,0,0];

        var myvect ;
        myvect = [0,0,0,0,0,0,0,0,0,0];

        var i=0;
        var j=0;

        for(i=0; i < employeesList.length; i++) {

            myvect = numberTel(employeesList[i].phone);
            for (j = 0; j < employeesList[i].phone.length; j++) {
                vect[j] += parseInt(myvect[j]);
            }
        }


        var aux;
        aux =[0,0,0,0,0,0,0,0,0,0];

        var finalVector ;
        finalVector = [0,0,0,0,0];
        var max = 0;

        for(i=0;i<5;i++){

            max = maxInd(vect,aux);
            finalVector.push(max);
            aux[max] = 1;
        }

        return finalVector;
    }

    function maxInd(vct,aux) {

        var i =0;
        var index = 0;
        var max = 0;
        for(i=0;i<vct.length;i++){
            if (parseInt(vct[i])> parseInt(max) && aux[i] === 0){
                max = vct[i];
                index = i;
            }
        }
        return index;

    }

    function average() {

        var mySum = 0;
        for(var i in employeesList){
            mySum += parseFloat(employeesList[i].salary);
        }

        var average =0 ;

        average= mySum / employeesList.length;
        return average;

    }


    function SortBy() {
        var input = document.getElementById("sort").value;
        switch (input) {
            case "1":
                employeesList.sort(compareFirst);
                showList();
                break;
            case "2":
                employeesList.sort(compareLast);
                showList();
                break;
            case "3":
                employeesList.sort(compareTel);
                showList();
                break;
            case "4":
                employeesList.sort(function (a, b) { return parseInt(a.salary) - parseInt(b.salary) });
                showList();
                break;
        }

    }

function compareTel(a,b) {
    return a.phone.localeCompare(b.phone);

}

function findme(){
    var input = document.getElementById("help").value;

    var i;
    var index = 0;
    var ok = 0;
    for(i = 0; i < employeesList.length; i++){
        if(input === employeesList[i].firstName ||
            input === employeesList[i].lastName ||
            input === employeesList[i].phone ||
            input === employeesList[i].salary ){
                ok = 1;
                index = i;
        }
    }

    if(ok === 0){
        alert("Nothing to show.");
    }else{
        vizualizare(index);
    }



}


