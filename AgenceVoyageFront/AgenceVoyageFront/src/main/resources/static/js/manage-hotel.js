document.addEventListener("DOMContentLoaded", () => {
    const hotelList = document.getElementById("hotelList");
    const addHotelBtn = document.getElementById("addHotelBtn");
    const hotelFormPopup = document.getElementById("hotelFormPopup");
    const saveBtn = document.getElementById("saveBtn");
    const cancelBtn = document.getElementById("cancelBtn");
    const hotelName = document.getElementById("hotelName");
    const hotelLocation = document.getElementById("hotelLocation");
    const hotelPrice = document.getElementById("hotelPrice");
    const popupTitle = document.getElementById("popupTitle");
    let isEditing = false;
    let currentEditId = null;

    // Hide the popup on page load
    hotelFormPopup.classList.add("hidden");

    // Fetch and display hotels
    const fetchHotels = async () => {
        try {
            const response = await fetch("/admin/hotels");
            if (!response.ok) throw new Error("Failed to fetch hotels.");
            const hotels = await response.json();
            hotelList.innerHTML = hotels
                .map(
                    (hotel) => `
                    <tr>
                        <td>${hotel.id}</td>
                        <td>${hotel.name}</td>
                        <td>${hotel.location}</td>
                        <td>${hotel.pricePerNight.toFixed(2)} MAD</td>
                        <td>
                            <div class="action-buttons">
                                <button 
                                    class="action-btn modify-btn" 
                                    data-id="${hotel.id}" 
                                    data-name="${hotel.name}" 
                                    data-location="${hotel.location}" 
                                    data-price="${hotel.pricePerNight}">
                                    ✏️
                                </button>
                                <button class="action-btn delete-btn" data-id="${hotel.id}">🗑️</button>
                            </div>
                        </td>
                    </tr>`
                )
                .join("");
        } catch (error) {
            console.error("Error fetching hotels:", error.message);
        }
    };

    // Add or update a hotel
    const addOrUpdateHotel = async () => {
        const payload = {
            name: hotelName.value.trim(),
            location: hotelLocation.value.trim(),
            pricePerNight: parseFloat(hotelPrice.value),
        };

        if (!payload.name || !payload.location || isNaN(payload.pricePerNight)) {
            alert("Tous les champs sont obligatoires.");
            return;
        }

        const method = isEditing ? "PUT" : "POST";
        const url = isEditing
            ? `/admin/hotels/${currentEditId}`
            : `/admin/hotels`;

        try {
            const response = await fetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Failed to save hotel.");
            }

            fetchHotels(); // Reload the table
            closePopup(); // Close the popup form
        } catch (error) {
            console.error("Error saving hotel:", error.message);
            alert(`Erreur: ${error.message}`);
        }
    };

    // Delete a hotel
    const deleteHotel = async (id) => {
        if (confirm("Êtes-vous sûr de vouloir supprimer cet hôtel ?")) {
            try {
                const response = await fetch(`/admin/hotels/${id}`, { method: "DELETE" });
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message || "Failed to delete hotel.");
                }
                fetchHotels(); // Reload the table after successful deletion
            } catch (error) {
                console.error("Error deleting hotel:", error.message);
                alert(`Erreur: ${error.message}`);
            }
        }
    };


    // Open popup for editing
    const editHotel = (id, name, location, price) => {
        isEditing = true;
        currentEditId = id;
        hotelName.value = name || "";
        hotelLocation.value = location || "";
        hotelPrice.value = price || "";
        popupTitle.textContent = "Modifier l'Hôtel";
        hotelFormPopup.classList.remove("hidden");
    };

    // Close the popup
    const closePopup = () => {
        hotelFormPopup.classList.add("hidden");
        hotelName.value = "";
        hotelLocation.value = "";
        hotelPrice.value = "";
        popupTitle.textContent = "Ajouter un Hôtel";
        isEditing = false;
        currentEditId = null;
    };

    // Open popup for adding
    addHotelBtn.addEventListener("click", () => {
        closePopup();
        hotelFormPopup.classList.remove("hidden");
    });

    // Attach event listeners for dynamically loaded content using event delegation
    hotelList.addEventListener("click", (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains("modify-btn")) {
            const name = target.dataset.name;
            const location = target.dataset.location;
            const price = target.dataset.price;
            editHotel(id, name, location, price);
        }

        if (target.classList.contains("delete-btn")) {
            deleteHotel(id);
        }
    });

    // Attach event listeners to buttons
    saveBtn.addEventListener("click", addOrUpdateHotel);
    cancelBtn.addEventListener("click", closePopup);

    // Fetch hotels on page load
    fetchHotels();
});
