var employeesList= [
    {
        firstName: 'John',
        lastName: 'King',
        phone: '0123456789',
        salary: 3245,
        euroSalary: ''
    },
    {
        firstName: 'Steven',
        lastName: 'Gerard',
        phone: '0123456789',
        salary: 4456,
        euroSalary: ''
    },
    {
        firstName: 'Mike',
        lastName: 'Bob',
        phone: '0123456789',
        salary: 2314,
        euroSalary: ''
    },
    {
        firstName: 'Emily',
        lastName: 'Hudson',
        phone: '0123456789',
        salary: 4500,
        euroSalary: ''
    }
];
var freqDig= [
    {
        Num: 0,
        Freq: 0,
    },
    {
        Num: 1,
        Freq: 0,
    },
    {
        Num: 2,
        Freq: 0,
    },
    {
        Num: 3,
        Freq: 0,
    },
    {
        Num: 4,
        Freq: 0,
    },
    {
        Num: 5,
        Freq: 0,
    },
    {
        Num: 6,
        Freq: 0,
    },
    {
        Num: 7,
        Freq: 0,
    },
    {
        Num: 8,
        Freq: 0,
    },
    {
        Num: 9,
        Freq: 0,
    }
];
function showList() {
    var myTable = '<table id="employeeTable" class="table table-striped"><tr><th>First Name</th><th>Last Name</th><th>Phone</th><th>Salary</th></tr>';

    for (var i in employeesList) {
        var sizePhone= employeesList[i].phone.size;
        for (var j=0;j<sizePhone;j++)
            freqDig[j].Freq++;
        myTable+= '<tr><td>'+employeesList[i].firstName+'</td><td>'+employeesList[i].lastName+'</td><td>'+employeesList[i].phone+'</td><td>'+employeesList[i].salary+'<button id="button" onclick="convertSalary('+i+')">Convert salary</button><td id="euroSum'+i+'">'+employeesList[i].euroSalary+'</td></td><td>'+'<button onclick="visualisation('+i+')">Vizualizare</button></td><td><button onclick="deleteRows('+i+')">Stergere</button></td></tr>';
    }
    myTable+='<tr><td>'+freqFirstName()+'</td><td>'+cntUniqLastName()+'</td><td>'+freqUsed5Digits()+'</td><td>'+salariesAverage()+'</td></tr>';
    myTable+= '</table>';
    var container = document.getElementById('listcontainer');
    container.innerHTML = myTable;
}
var Employee = function(firstName,lastName,phone,salary){
    this.firstName=firstName;
    this.lastName=lastName;
    this.phone=phone;
    this.salary=salary;
}

function addEmployee() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var phone = document.getElementById("phone").value;
    var salary = document.getElementById("salary").value;
    employeesList.push(new Employee(firstName,lastName,phone,salary));
    showList();
}

function sumOfSalaries() {
    var TotalSalary=0;
    for (var i in employeesList){
        TotalSalary += parseInt(employeesList[i].salary.toString());
    }
    var container = document.getElementById('listcontainer');
    container.innerHTML = TotalSalary;
}

function deleteLastEmployee() {
    employeesList.pop();
    showList();
}

function convertSalary(i) {
    // alert("Intra aici");
    employeesList[i].euroSalary= parseFloat((employeesList[i].salary/4.5).toString());
    var container = document.getElementById('euroSum'+i);
    container.innerHTML= employeesList[i].euroSalary;
}

function visualisation(i) {
    alert(employeesList[i].firstName+' '+employeesList[i].lastName+' '+employeesList[i].phone+' '+employeesList[i].salary);
}

function deleteRows(i) {
    employeesList.splice(i,1);
    showList();
}

function freqFirstName() {
}

function cntUniqLastName() {

}

function freqUsed5Digits() {
    freqDig=freqDig.sort(function(a,b) {
        if (a.Freq>b.Freq)
            return -1;
        if (a.Freq<b.Freq)
            return 1;
        return 0;
    })
}

function salariesAverage() {
    var listSize=employeesList.length;
    var avg=0;
    for (var i in employeesList)
        avg+= parseFloat(employeesList[i].salary.toString());
    return avg/listSize;
}