document.addEventListener("DOMContentLoaded", function (){
    console.log('DOCUMENT READY!!');

    fetchGetAdmins();

});

async function fetchGetAdmins() {
    console.log('fetchGetAdmins()');

    try {
        let response = await fetch('/admin/admins', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok!');
        }

        console.log('fetchGetAdmins() COMMUNICATION SUCCESS!!');

        let data = await response.json();
        console.log('data: ', data);
        let admins = data.admins
        for (let i = 0; admins.length; i++) {
            let template = document.querySelector('#list-template').content.cloneNode(true);
            template.querySelector('.no').textContent = admins[i].no;
            template.querySelector('.id').textContent = admins[i].id;
            template.querySelector('select[name="authority"]').value = admins[i].authorityDto.no;
            template.querySelector('.mail').textContent = admins[i].mail;
            template.querySelector('.phone').textContent = admins[i].phone;
            template.querySelector('.reg_date').textContent = admins[i].reg_date;
            template.querySelector('.mod_date').textContent = admins[i].mod_date;
            document.querySelector('#section_wrap tbody').appendChild(template);
        }

    } catch (error) {
        console.log('fetchGetAdmins() COMMUNICATION ERROR!!', error);

    }

}