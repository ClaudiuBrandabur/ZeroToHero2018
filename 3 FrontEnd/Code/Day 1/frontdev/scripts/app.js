var employeesList = [
    {
        firstName: 'John',
        lastName: 'King',
        phone: '0123456789',
        salary: 4500
    },
    {
        firstName: 'Steven',
        lastName: 'Gerrard',
        phone: '0123456789',
        salary: 4500
    },
    {
        firstName: 'Diana',
        lastName: 'Ross',
        phone: '0123456789',
        salary: 4500
    },
    {
        firstName: 'Mike',
        lastName: 'Bob',
        phone: '0123456789',
        salary: 4500
    },
    {
        firstName: 'Emily',
        lastName: 'Hudson',
        phone: '0123456789',
        salary: 4500
    }

];

var facts = [
'Chuck Norris threw a grenade and killed 50 people, then it exploded.',
'Chuck Norris counted to infinity. Twice.',
'Chuck Norris can kill two stones with one bird.',
'Chuck Norris can hear sign language.'
];

var x = false;

function showList() {
    var avg = averageSalary();
    var myTable = '<table id="employeeTable" class="table table-striped"><tr><th>First Name</th><th>Last Name</th><th>Phone</th><th>Salary</th><th><button onclick="convert()">Convert salary</button></th><th> </th><th></th></tr>';
    for (var i in employeesList) {
        myTable += '<tr><td>' + employeesList[i].firstName + '</td><td>' + employeesList[i].lastName + '</td><td>' + employeesList[i].phone + '</td> <td>' + employeesList[i].salary + '</td><td class="eurosalary"></td><td><button onclick="vizualizare('+i+')">Vizualizare</button></td><td><button onclick="stergere('+i+')">Stergere</button></td></tr>';
    }
    myTable +='<tr><td>' + '1' + '</td><td>' + '2' + '</td><td>' + '3' + '</td><td>' + parseInt(avg) + '</td></tr>';
    myTable += '</table>'
    var container = document.getElementById('listcontainer');
    container.innerHTML = myTable;
}

function convert() {
    x = true;
    var i = 0;
    var y = document.getElementsByClassName("eurosalary");
    for (var j in y) {
        y[j].innerHTML = euroSalary(x, employeesList[i].salary);
        i++;
    }

}

var Employee = function (firstName, lastName, phone, salary) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.salary = salary;
}

function addEmployee() {
    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var phone = document.getElementById("phone").value;
    var salary = document.getElementById("salary").value;
    employeesList.push(new Employee(firstName, lastName, phone, salary));
    showList();
    salaryTotal();
}

function salaryTotal() {
    var salaryTotal = 0;
    for (var i in employeesList) {
        salaryTotal += parseInt(employeesList[i].salary.toString());

    }
    var salariuTotal = document.getElementById("salaryTotal");
    salariuTotal.innerText = salaryTotal;
}

function deleteLastEmployee() {
    employeesList.pop();
    showList();
    salaryTotal();

}

function euroSalary(x, salary) {

    if (x == true)
        return salary / 4.5;
    if (x == false)
        return ' ';
}

function vizualizare(i) {
    alert('First Name: ' + employeesList[i].firstName + '    Last Name: ' + employeesList[i].lastName + '    Phone: ' + employeesList[i].phone + '    Salary: ' + employeesList[i].salary);
}

function stergere(i){
    employeesList.splice(i, 1);
    showList();
}

function averageSalary(){
    var tot = 0;
    for(var i in employeesList){
        tot += parseInt(employeesList[i].salary.toString());
    }
    var average = parseFloat(tot.toString()) / employeesList.length;
    return average;
}

function sortBy() {
    var num = document.getElementById("in").value;
    if(num == 1){
        employeesList = employeesList.sort(function (a, b) {
            if(a.firstName.toUpperCase() < b.firstName.toUpperCase())
                return -1;
            else if(a.firstName.toUpperCase() > b.firstName.toUpperCase())
                return 1;
            else
                return 0;
        })
        showList();
    }
    if(num == 2){
        employeesList = employeesList.sort(function (a, b) {
            if(a.lastName.toUpperCase() < b.lastName.toUpperCase())
                return -1;
            else if(a.lastName.toUpperCase() > b.lastName.toUpperCase())
                return 1;
            else
                return 0;
        })
        showList();
    }
    if(num == 3){
        employeesList = employeesList.sort(function (a, b) {
            if(a.phone < b.phone)
                return -1;
            else if(a.phone > b.phone)
                return 1;
            else
                return 0;
        })
        showList();
    }
    if(num == 4){
        employeesList = employeesList.sort(function (a, b) {
            if(a.salary < b.salary)
                return -1;
            else if(a.salary > b.salary)
                return 1;
            else
                return 0;
            })
        showList();
    }
}

function filter() {
    var input = document.getElementById("filter");
    var filter = input.value.toUpperCase();
    var table = document.getElementById("employeeTable");
    var tr = table.getElementsByTagName("tr");

    for (var i = 0; i < tr.length; i++) {

        if (tr[i].innerHTML.toUpperCase().indexOf(filter) > -1) {
            tr[i].style.display = "";
        } else {
            tr[i].style.display = "none";
        }

    }
}