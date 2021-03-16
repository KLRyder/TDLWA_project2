let createTaskModal = document.getElementById('createTaskModal');
let updateTaskListModal = document.getElementById('updateTaskListModal');
let updateTaskModal = document.getElementById('updateTaskModal');
const apiURL = 'http://localhost:8080/'

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
    let taskDate = button.getAttribute('data-bs-taskDate')

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
        body: JSON.stringify({"name": listName.value})
    }).then(res => res.json())
        .then((data) => console.log(`Request succeeded with JSON response ${data}`))
        .catch((error) => console.error(`Request failed ${error}`))
}