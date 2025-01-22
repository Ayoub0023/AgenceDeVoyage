document.addEventListener("DOMContentLoaded", () => {
    const tabs = document.querySelectorAll(".tab");
    const sections = document.querySelectorAll(".category-section");
    const cartItems = document.getElementById("cart-items");
    const totalPriceEl = document.getElementById("total-price");
    const popup = document.getElementById("popup");
    const popupContent = document.querySelector(".popup-content");
    let currentItem = null; // To store the current hotel, flight, or car data
    const cart = [];
    let totalPrice = 0;

    // Tab navigation
    tabs.forEach(tab => {
        tab.addEventListener("click", () => {
            tabs.forEach(t => t.classList.remove("active"));
            tab.classList.add("active");

            const category = tab.getAttribute("data-category");
            sections.forEach(section => {
                section.id === category
                    ? section.classList.remove("hidden")
                    : section.classList.add("hidden");
            });
        });
    });

    // Add to cart - open popup
    document.body.addEventListener("click", event => {
        if (event.target.classList.contains("add-to-cart")) {
            const item = event.target.closest(".item, .flight-card, .car-card");
            const id = item.getAttribute("data-id");
            const price = parseFloat(item.getAttribute("data-price"));
            const name = item.querySelector("h3").innerText;

            currentItem = { id, name, price };

            if (item.classList.contains("flight-card")) {
                showPopupForm("flight");
            } else if (item.classList.contains("car-card")) {
                showPopupForm("car");
            } else {
                showPopupForm("hotel");
            }
        }
    });

    // Show popup form based on type
    const showPopupForm = (type) => {
        let formHtml = "";

        if (type === "flight") {
            formHtml = `
                <label for="ticket-quantity">Number of Tickets:</label>
                <input type="number" id="ticket-quantity" min="1" required />
                <div class="popup-buttons">
                    <button type="button" id="save-btn" class="popup-save">Enregistrer</button>
                    <button type="button" id="cancel-btn" class="popup-cancel">Annuler</button>
                </div>
            `;
        } else if (type === "car") {
            formHtml = `
                <label for="rental-start-date">Rental Start Date:</label>
                <input type="date" id="rental-start-date" required />
                <label for="rental-end-date">Rental End Date:</label>
                <input type="date" id="rental-end-date" required />
                <div class="popup-buttons">
                    <button type="button" id="save-btn" class="popup-save">Enregistrer</button>
                    <button type="button" id="cancel-btn" class="popup-cancel">Annuler</button>
                </div>
            `;
        } else if (type === "hotel") {
            formHtml = `
                <label for="checkin-date">Check-in Date:</label>
                <input type="date" id="checkin-date" required />
                <label for="checkout-date">Check-out Date:</label>
                <input type="date" id="checkout-date" required />
                <label for="room-size">Room Size:</label>
                <select id="room-size" required>
                    <option value="1">1 Person</option>
                    <option value="2">2 Persons</option>
                    <option value="3">3 Persons</option>
                </select>
                <div class="popup-buttons">
                    <button type="button" id="save-btn" class="popup-save">Enregistrer</button>
                    <button type="button" id="cancel-btn" class="popup-cancel">Annuler</button>
                </div>
            `;
        }

        popupContent.innerHTML = formHtml;

        // Attach event listeners for new buttons
        const saveBtn = document.getElementById("save-btn");
        const cancelBtn = document.getElementById("cancel-btn");

        saveBtn.addEventListener("click", saveReservation);
        cancelBtn.addEventListener("click", () => {
            popup.classList.add("hidden");
        });

        popup.classList.remove("hidden");
    };

    // Save reservation details
    const saveReservation = () => {
        if (!currentItem) return;

        const isFlight = document.getElementById("ticket-quantity");
        const isCar = document.getElementById("rental-start-date") && document.getElementById("rental-end-date");
        const isHotel = document.getElementById("checkin-date");

        if (isFlight) {
            const ticketQuantity = parseInt(isFlight.value);
            if (ticketQuantity > 0) {
                const totalFlightPrice = ticketQuantity * currentItem.price;

                cart.push({
                    name: `${currentItem.name} (${ticketQuantity} tickets)`,
                    totalPrice: totalFlightPrice.toFixed(2),
                });

                totalPrice += totalFlightPrice;
                updateCart();
                popup.classList.add("hidden");
            } else {
                alert("Please enter a valid number of tickets.");
            }
        } else if (isCar) {
            const rentalStartDate = new Date(document.getElementById("rental-start-date").value);
            const rentalEndDate = new Date(document.getElementById("rental-end-date").value);

            const diffTime = Math.abs(rentalEndDate - rentalStartDate);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

            if (diffDays > 0) {
                const totalCarPrice = diffDays * currentItem.price;

                cart.push({
                    name: `${currentItem.name} (${diffDays} days, ${rentalStartDate.toLocaleDateString()} - ${rentalEndDate.toLocaleDateString()})`,
                    totalPrice: totalCarPrice.toFixed(2),
                });

                totalPrice += totalCarPrice;
                updateCart();
                popup.classList.add("hidden");
            } else {
                alert("Please select valid rental dates.");
            }
        } else if (isHotel) {
            const checkinDate = new Date(document.getElementById("checkin-date").value);
            const checkoutDate = new Date(document.getElementById("checkout-date").value);

            const diffTime = Math.abs(checkoutDate - checkinDate);
            const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));

            if (diffDays > 0) {
                const totalHotelPrice = diffDays * currentItem.price;

                cart.push({
                    name: `${currentItem.name} (${diffDays} days)`,
                    totalPrice: totalHotelPrice.toFixed(2),
                });

                totalPrice += totalHotelPrice;
                updateCart();
                popup.classList.add("hidden");
            } else {
                alert("Please select valid dates!");
            }
        }
    };

    // Update cart
    const updateCart = () => {
        cartItems.innerHTML = cart
            .map(
                item => `
            <li>
                ${item.name}
                <span>${item.totalPrice} MAD</span>
            </li>`
            )
            .join("");

        totalPriceEl.textContent = totalPrice.toFixed(2);
    };
});
