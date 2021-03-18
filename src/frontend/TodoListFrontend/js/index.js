let createTaskModal = document.getElementById('createTaskModal');
let updateTaskListModal = document.getElementById('updateTaskListModal');
let updateTaskModal = document.getElementById('updateTaskModal');
const apiURL = 'http://localhost:8080/'

//init masonary grid
let listContainer = document.querySelector('#listContainer');
let msn = new Masonry(listContainer, {
    // options
    percentPosition: true,
    itemSelector: '.msnry-Card'
});

createTaskModal.addEventListener('show.bs.modal', function (event) {

    // get task information from triggering button
    let button = event.relatedTarget;
    let listId = button.getAttribute('data-bs-listID');
    let listName = button.getAttribute('data-bs-listName');

    // Update the modal's content.
    let modalTitle = createTaskModal.querySelector('.modal-title');
    let modelListId = createTaskModal.querySelector('#listID');

    modalTitle.textContent = 'New task: ' + listName;
    modelListId.value = listId;
})

updateTaskModal.addEventListener('show.bs.modal', function (event) {

    // get task information from triggering button
    let button = event.relatedTarget;
    let taskId = button.getAttribute('data-bs-taskID');
    let taskName = button.getAttribute('data-bs-taskName');
    let taskDate = button.getAttribute('data-bs-taskDate');

    // Update the modal's content.
    let modalName = updateTaskModal.querySelector('#update-task-name');
    let modelTaskId = updateTaskModal.querySelector('#update-taskID');
    let modelDate = updateTaskModal.querySelector('#update-Due-Date')

    modalName.setAttribute("value", taskName);
    modelTaskId.setAttribute("value", taskId);
    modelDate.setAttribute("value", taskDate);
})

updateTaskListModal.addEventListener('show.bs.modal', function (event) {

    // get list information from triggering button
    let button = event.relatedTarget;
    let listId = button.getAttribute('data-bs-listID');
    let listName = button.getAttribute('data-bs-listName');

    // Update the modal's content.
    let modalListName = updateTaskListModal.querySelector('#update-list-name');
    let modelListId = updateTaskListModal.querySelector('#update-listID');

    modalListName.setAttribute("value", listName);
    modelListId.setAttribute("value", listId);
})

function toggleDone(taskId) {
    let taskHeading = document.getElementById('heading' + taskId);
    let taskButton = taskHeading.querySelector("button")
    if (taskButton.innerHTML.substr(0, 3) === '<s>') {
        taskButton.innerHTML = taskButton.innerHTML.substr(3, taskButton.innerHTML.length - 7)
    } else {
        taskButton.innerHTML = '<s>' + taskButton.innerHTML
    }
}

let submitNewList = () => {
    let listName = document.querySelector('#List-name');
    fetch(apiURL + 'lists', {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "name": listName.value
        })
    }).then(res => res.json())
        .then((data) => {
            displayList(data);
        })
        .catch((error) => console.error(`Request failed ${error}`));
}

let submitNewTask = () => {
    let taskDescription = document.getElementById("task-name").value;
    let complete = false;
    let taskListId = document.getElementById("listID").value.substr(8);
    fetch(apiURL + 'tasks', {
        method: 'post',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "taskList": {
                "id": taskListId
            },
            "description": taskDescription,
            "complete": complete
        })
    }).then(res => res.json())
        .then((data) => {
            displayTask(data, taskListId);
        })
        .catch((error) => console.error(`Request failed ${error}`))
}

let updateList = () => {
    let listName = document.querySelector('#update-list-name').value;
    let listId = document.querySelector('#update-listID').value.substr(8);
    fetch(apiURL + 'lists', {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "id": listId,
            "name": listName
        })
    }).then(res => {
        if (res.status === 200) {
            updateListNameOnPage(listId, listName)
        } else {
            console.error(`Request failed ${res.body}`)
        }
    }).catch((error) => console.error(`Request failed ${error}`))
}

let updateTask = () => {
    let taskDescription = document.querySelector('#update-task-name').value;
    let taskId = document.querySelector('#update-taskID').value;
    let taskDone = document.getElementById('task-'+taskId).getAttribute("data-isDone");
    // let taskDate = document.querySelector('#update-Due-Date').value;
    fetch(apiURL + 'tasks', {
        method: 'put',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            "id": taskId,
            "description": taskDescription,
            // "dueDate": taskDate,
            "complete": taskDone
        })
    }).then(res => {
        if (res.status === 200) {
            updateTaskOnPage(taskId, taskDescription)
        } else {
            console.error(`Request failed ${res.body}`)
        }
    }).catch((error) => console.error(`Request failed ${error}`))
}

let updateListNameOnPage = (id, name) => {
    let title = document.getElementById('list-name' + id);
    let editButton = document.getElementById('edit-list' + id);
    let addTaskButton = document.getElementById('add-task' + id);

    title.innerText = name;
    editButton.setAttribute("data-bs-listName", name);
    addTaskButton.setAttribute("data-bs-listName", name);
}

let updateTaskOnPage = (id, name) => {
    let taskName = document.getElementById('heading'+id).querySelector(".accordion-button");
    let editButton = document.getElementById('edit-task'+id);

    taskName.innerText = name;
    editButton.setAttribute("data-bs-taskName", name);
}

let deleteList = (id) => {
    fetch(apiURL + 'lists?id=' + id, {
        method: 'delete'
    }).then(res => {
        if (res.status === 200) {
            let toDelete = document.getElementById("taskList" + id);
            msn.remove(toDelete);
            msn.layout();
        } else {
            console.error(`Request failed ${res.body}`)
        }
    }).catch((error) => console.error(`Request failed ${error}`))
}

let displayList = taskListJSON => {
    let template = document.querySelector('#taskListTemplate');
    let taskClone = template.content.cloneNode(true);

    // extract from document fragment
    let node = taskClone.getElementById("taskList_listId");
    node.id = "taskList" + taskListJSON.id;
    for (let i = 0; i < 9; i++) {
        node.innerHTML = node.innerHTML.replace("_listId", taskListJSON.id);
    }

    for (let i = 0; i < 3; i++) {
        node.innerHTML = node.innerHTML.replace("_listName", taskListJSON.name);
    }

    listContainer.append(node);
    msn.appended(node);
    msn.layout();

    let tasks = taskListJSON.tasks;
    for (let i = 0; i<tasks.length;i++){
        displayTask(tasks[i],taskListJSON.id);
    }
}

let displayTask = (taskJSON, taskListID) => {
    let template = document.querySelector('#taskTemplate');
    let taskClone = template.content.cloneNode(true);

    // extract from document fragment
    let node = taskClone.getElementById('task-_taskId');
    node.id = 'task-' + taskJSON.id;

    for (let i = 0; i < 11; i++) {
        node.innerHTML = node.innerHTML.replace("_taskId", taskJSON.id);
    }

    for (let i = 0; i < 3; i++) {
        node.innerHTML = node.innerHTML.replace("_taskName", taskJSON.description);
    }

    node.innerHTML = node.innerHTML.replace("_listId", taskListID);

    document.getElementById('taskListAccordion'+taskListID).append(node);

    if(taskJSON.complete === true){
        toggleDone(taskJSON.id);
    }
}

let getAllLists = () => {
    fetch(apiURL + 'lists').then(res => res.json())
        .then((data) => {
            for (let i = 0; i < data.length; i++) {
                displayList(data[i]);
            }
        })
        .catch((error) => console.error(`Request failed ${error}`))
}

getAllLists();