// function fetchScript(url, method) {
async function orderItemFetchData() {
    try{
        const response = await fetch('/newOrderItems', {
            method: 'POST',
            body: JSON.stringify(
                {
                    id: document.getElementById('orderItemId').value,
                    product: document.getElementById('product').value.trim(),
                    order: document.getElementById('order').value.trim(),
                    // product:1,
                    // order: 1,
                }
            ),
            headers: {
                'Content-Type': 'application/json',
            }
        });

        console.log(response);
        console.log(response.body);

        if(!response.ok){
            throw new Error(response.statusText);
        }

        // const contentDiv = document.getElementById('contentDiv');
        // contentDiv.innerHTML = response.body;

        // todo : has error
        // const json = await response.json();
        // console.log("Info"  + JSON.stringify(json));
        console.log(response.text());

    }catch(error){
        console.log("Error" + error);
    }
}


// ${'btn'}.onclick(function(){
//     $.ajax({
//         url:'/',
//         method: 'GET',
//         success:function(response){
//
//         },
//         error:function(error){
//
//         }
//     })
// })