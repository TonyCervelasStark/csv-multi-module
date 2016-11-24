function RestService(baseUrl) {
	this.baseUrl = baseUrl
}

RestService.prototype = {
		getAll: function(callback) {
			$.get(this.baseUrl, callback)
		},
		
		getOne: function(id, callback) {
			$.get(`${this.baseUrl}/${id}`, callback)
		},
		
		createOrUpdate(employee, callback) {
			$.ajax(this.baseUrl, {
				method: employee.id<=0 ? 'put' : 'post',
				dataType: 'json',
				contentType: 'application/json',
				data: JSON.stringify(employee),
				success: callback
			})
		},
		
		delete: function(id, callback) {
			$.ajax(`${this.baseUrl}/${id}`, {
				method: 'delete',
				success: () => callback()
			})
		}
}

let editedEmployee = null

function getTableRowForEmployee(employee) {
	return `<tr data-id='${employee.id}'>
				<td>${employee.name}</td>
				<td>${employee.firstname}</td>
				<td>${employee.agency}</td>
				<td><div class="buttonBar">
					<a class="editButton btn-floating yellow"><i class="material-icons">edit</i></a>
					<a class="deleteButton btn-floating red"><i class="material-icons">delete</i></a>
				</div></td>
			</tr>`
}

function showTable() {
	editedEmployee = null
	
	$('#crudFlipContainer').removeClass('flip')
}

function showCreateForm() {
	editedEmployee = null
	
	$('#name').val('')
	$('#firstname').val('')
	$('#agency').val('')

	
	$('#crudFlipContainer').addClass('flip')
	
	$('#name').focus()
}

function showEditForm(employee) {
	editedEmployee = employee
	
	$('#name').val(employee.name)
	$('#firstname').val(employee.firstname)
	$('#agency').val(employee.agency)
	
	$('#crudFlipContainer').addClass('flip')
	
	$('#name').focus()
}

$(function() {
	let service = new RestService('formation/employee')
	
	// Attention ce n'est pas forcément recommandé d'écrire de façon si condensée !
	// Avez-vous des difficultés à lire cette ligne de code ?
	service.getAll( (employees) => $('table.Employee').append( $(employees.map(getTableRowForEmployee).join('')) ) )
	
	$("table.Employee").on("click", ".deleteButton", function() {
		let tableRow = $(this).closest("tr[data-id]")
		let id = tableRow.attr("data-id")
		
		service.delete(id, () => tableRow.remove())
	})
	
	$("table.Employee").on("click", ".editButton", function() {
		let tableRow = $(this).closest("tr[data-id]")
		let id = tableRow.attr("data-id")
		
		service.getOne(id, showEditForm)
	})
	
	$('#createButton').click(showCreateForm)
	
	$('#okButton').click(function() {
		employee = editedEmployee
		if( !employee )
			employee = { id: -1 }
		
		employee.name = $('#name').val()
		employee.firstname = $('#firstname').val()
		employee.agency = $('#agency').val()

		
		service.createOrUpdate(employee, function(employee) {
			let rowHtml = getTableRowForEmployee(employee)
			
			let employeeRow = $(`table.Employee tr[data-id=${employee.id}]`)
			if( employeeRow.length )
				employeeRow.replaceWith(rowHtml)
			else
				$('table.Employee').append( $(rowHtml) )
		})
		
		showTable()
	})
})