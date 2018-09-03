var employeesList = [
    {
        firstName: 'John',
        lastName: 'King',
        phone: '0123456789',
        salary: 4500
    },
    {
        firstName: 'Steven',
        lastName: 'Gerard',
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

function showList() {
    var myTable = '<table class="table table-striped">' +
        '<tr>' +
        '<th>First Name</th>' +
        '<th>Last Name</th>' +
        '<th>Phone</th>' +
        '<th>Salary</th>' +
        '<th><button onclick="convertSalary()">Convert Salary</button></th>' +
        '<th>Vizualizare</th>' +
        '<th>Stergere</th>'+
        '</tr>';
    for (var i in employeesList) {
        myTable +=

            '<tr>' +
            '<td>' + employeesList[i].firstName + '</td>' +
            '<td>' + employeesList[i].lastName + '</td>' +
            '<td>' + employeesList[i].phone + '</td>' +
            '<td>' + employeesList[i].salary + '</td>' +
            '<td><th><button onclick="visualise('+i+')">Vizualizare</button></td>' +
            '<td><button onclick="stergere('+i+')">Stergere</button></td>' +
            '</tr>';
    }
    myTable += ' </table>';
    var container = document.getElementById('listcontainer');
    container.innerHTML = myTable;
}

var Employee = function (firstName, lastName, phone, salary, eurovalue) {

    this.firstName = firstName;
    this.lastName = lastName;
    this.phone = phone;
    this.salary = salary;
};

function addEmployee() {

    var firstName = document.getElementById("firstName").value;
    var lastName = document.getElementById("lastName").value;
    var phone = document.getElementById("phone").value;
    var salary = document.getElementById("salary").value;
    employeesList.push(new Employee(firstName, lastName, phone, salary));
    showList();
}
function input(){
    var nr = document.getElementById("nr").value;
    nr.push(new nr);
}
function salaryTotal() {
    var total = 0;
    for (var i in employeesList) {
        total+=eval(employeesList[i].salary);
    }
    document.getElementById("totalSalary").innerHTML = 'Total salary is: '+ total;
}

function deleteLast() {
    employeesList.pop();
    showList();
}
function convertSalary() {
    var myTable = '<table class="table table-striped">' +
        '<tr>' +
        '<th>First Name</th>' +
        '<th>Last Name</th>' +
        '<th>Phone</th>' +
        '<th>Salary</th>' +
        '<th><button onclick="convertSalary()">Convert Salary</button></th>' +
        '<th>Vizualizare</th>' +
        '<th>Stergere</th>' +
        '</tr>';
    for (var i in employeesList) {
        myTable +=

            '<tr>' +
            '<td>' + employeesList[i].firstName + '</td>' +
            '<td>' + employeesList[i].lastName + '</td>' +
            '<td>' + employeesList[i].phone + '</td>' +
            '<td>' + employeesList[i].salary + '</td>' +
            '<td>' + parseFloat(employeesList[i].salary/4.5).toFixed(2) + '</td>' +
            '<td><button onclick="visualise('+i+')">Vizualizare</button></td>' +
            '<td><button onclick="stergere('+i+')">Stergere</button></td>' +
            '</tr>';
    }
    myTable += ' </table>';
    var container = document.getElementById('listcontainer');
    container.innerHTML = myTable;
}
function visualise(i) {
       alert( employeesList[i].firstName+" "+employeesList[i].lastName+" "+employeesList[i].phone+" "+employeesList[i].salary);

}
function stergere(i) {
    employeesList.splice(i,1);
    showList()

}
