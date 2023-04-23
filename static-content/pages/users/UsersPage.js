

function UsersPage() {
    const list = document.createElement('ul');

    const items = ['id', 'name', 'email', 'token'];

    items.forEach(item => {
        const li = document.createElement('li');
        li.textContent = item;
        list.appendChild(li);
    });

    return list

}

export default UsersPage;