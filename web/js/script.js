'use strict';

window.onload = function () {
    loadCoupons();
    loadShops();
};

function loadShops() {
    axios.get('/rest/shops')
        .then(function (response) {
            var shops = response.data;
            for(var i=0; i<shops.length; i++) {
                var shop = shops[i];
                var option = document.createElement("option");
                option.text = shop.name;
                option.value = shop.id;
                document.getElementById("shop_id").appendChild(option);
            }
        })
        .catch(function (error) {
            // handle error
            console.error(error);
        });
}

function loadCoupons() {
    // Make a request for a user with a given ID
    axios.get('/rest/coupons')
        .then(function (response) {
            // handle success
            var result = response.data;
            console.log(response);

            // Dohvati tabelu iz DOM-a po id-u
            var table = document.getElementById("coupons-tbl");

            // Dohvati prvi tbody
            var oldTBody = table.tBodies[0];
            var newTBody = document.createElement("tBody");

            for (var i = 0; i < result.length; i++) {

                var bRow = document.createElement("tr"); // Kreiraj novi red

                // Postavi vrednosti za taj red iz rezultata sa servera
                var productName = document.createElement("td");
                productName.innerHTML = result[i].product;
                var shopName = document.createElement("td");
                shopName.innerHTML = result[i].shop.name;
                var discountedPrice = document.createElement("td");
                discountedPrice.style.background = '#00FF00';
                discountedPrice.innerHTML = result[i].discountedPrice;
                var originalPrice = document.createElement("td");
                originalPrice.style.background = '#FF0000';
                originalPrice.innerHTML = result[i].originalPrice;
                var percent = document.createElement("td");
                percent.innerHTML = ((result[i].originalPrice - result[i].discountedPrice)*100/result[i].originalPrice).toFixed(2);;
                var action = document.createElement("td");
                action.innerHTML = '<button type="button" onclick="deleteCoupon(this, '+result[i].id+')">DELETE</button>';

                bRow.appendChild(productName);
                bRow.appendChild(shopName);
                bRow.appendChild(discountedPrice);
                bRow.appendChild(originalPrice);
                bRow.appendChild(percent);
                bRow.appendChild(action);

                newTBody.appendChild(bRow)
            }

            // Zameni postojeci sa novim tBody-jem.
            // Na taj nacin ce uvek da se ispisuju svezi elementi a stari da nestanu.
            table.replaceChild(newTBody, oldTBody)

        })
        .catch(function (error) {
            // handle error
            console.error(error);
        });
}

function deleteCoupon(event, id) {
    console.log("delete "+id);
    axios.delete('/rest/coupons/'+id)
        .then(function (response) {
            event.parentElement.parentElement.remove();
            console.log("deleted");
        })
        .catch(function (error) {
            // handle error
            console.error(error);
        });
}

var form = document.getElementById('create-coupon-form');
if (form.attachEvent) {
    // IE support
    form.attachEvent("submit", processForm);
} else {
    form.addEventListener("submit", processForm);
}

function processForm(e) {
    if (e.preventDefault) {
        e.preventDefault();
    }
    var formData = new FormData(e.target);

    var product = formData.get("product");
    var discounted_price = formData.get("discounted_price");
    var original_price = formData.get("original_price");
    var shopId = formData.get("shop_id");

    createCoupon(shopId, product, discounted_price, original_price);
    // Obavezno vratiti false da bi se pregazilo default-no ponasanje prilikom submit-a.
    return false;
}

function createCoupon(shopId, product, discountedPrice, originalPrice) {
    axios.post('rest/shops/'+shopId+'/coupons', {
            product: product,
            discountedPrice: discountedPrice,
            originalPrice: originalPrice
        })
        .then(function (response) {
            var result = response.data;
            // Dohvati tabelu iz DOM-a po id-u
            var table = document.getElementById("coupons-tbl");
            // Dohvati prvi tbody
            var tBody = table.tBodies[0];

            var bRow = document.createElement("tr"); // Kreiraj novi red

            // Postavi vrednosti za taj red iz rezultata sa servera
            var productName = document.createElement("td");
            productName.innerHTML = result.product;
            var shopName = document.createElement("td");
            shopName.innerHTML = result.shop.name;
            var discountedPrice = document.createElement("td");
            discountedPrice.style.background = '#00FF00';
            discountedPrice.innerHTML = result.discountedPrice;
            var originalPrice = document.createElement("td");
            originalPrice.style.background = '#FF0000';
            originalPrice.innerHTML = result.originalPrice;
            var percent = document.createElement("td");
            percent.innerHTML = ((result.originalPrice - result.discountedPrice)*100/result.originalPrice).toFixed(2);;
            var action = document.createElement("td");
            action.innerHTML = '<button type="button" onclick="deleteCoupon(this, '+result.id+')">DELETE</button>';

            bRow.appendChild(productName);
            bRow.appendChild(shopName);
            bRow.appendChild(discountedPrice);
            bRow.appendChild(originalPrice);
            bRow.appendChild(percent);
            bRow.appendChild(action);

            tBody.appendChild(bRow)
        })
        .catch(function (error) {
            console.log(error);
        });
}