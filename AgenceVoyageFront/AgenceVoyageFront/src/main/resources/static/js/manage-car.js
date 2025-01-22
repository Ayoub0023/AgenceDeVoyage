document.addEventListener("DOMContentLoaded", () => {
    const carList = document.getElementById("carList");
    const addCarBtn = document.getElementById("addCarBtn");
    const carFormPopup = document.getElementById("carFormPopup");
    const saveBtn = document.getElementById("saveBtn");
    const cancelBtn = document.getElementById("cancelBtn");
    const carModel = document.getElementById("carModel");
    const carAgency = document.getElementById("carAgency");
    const carPrice = document.getElementById("carPrice");
    const popupTitle = document.getElementById("popupTitle");
    let isEditing = false;
    let currentEditId = null;

    // Hide the popup on page load
    carFormPopup.classList.add("hidden");

    // Fetch and display cars
    const fetchCars = async () => {
        try {
            const response = await fetch("/admin/cars");
            if (!response.ok) throw new Error("Failed to fetch cars.");
            const cars = await response.json();
            carList.innerHTML = cars
                .map(
                    (car) => `
                    <tr>
                <td>${car.id}</td>
                <td>${car.model}</td>
                <td>${car.agency}</td>
                <td>${car.pricePerDay.toFixed(2)} MAD</td>
                        <td>
                            <div class="action-buttons">
                                <button 
                                    class="action-btn modify-btn" 
                                    data-id="${car.id}" 
                                    data-model="${car.model}" 
                                    data-agance="${car.agency}" 
                                    data-price="${car.pricePerDay}">
                                    ✏️
                                </button>
                                <button class="action-btn delete-btn" data-id="${car.id}">🗑️</button>
                            </div>
                        </td>
                    </tr>`
                )
                .join("");
        } catch (error) {
            console.error("Error fetching cars:", error.message);
        }
    };

    // Add or update a car
    const addOrUpdateCar = async () => {
        const payload = {
            model: carModel.value.trim(),
            agency: carAgency.value.trim(),
            pricePerDay: parseFloat(carPrice.value),
        };

        if (!payload.model || !payload.agency || isNaN(payload.pricePerDay)) {
            alert("Tous les champs sont obligatoires.");
            return;
        }

        const method = isEditing ? "PUT" : "POST";
        const url = isEditing
            ? `/admin/cars/${currentEditId}`
            : `/admin/cars`;

        try {
            const response = await fetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Failed to save car.");
            }

            fetchCars(); // Reload the table
            closePopup(); // Close the popup form
        } catch (error) {
            console.error("Error saving car:", error.message);
            alert(`Erreur: ${error.message}`);
        }
    };

    // Delete a car
    const deleteCar = async (id) => {
        if (confirm("Êtes-vous sûr de vouloir supprimer cette voiture ?")) {
            try {
                const response = await fetch(`/admin/cars/${id}`, { method: "DELETE" });
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(errorData.message || "Failed to delete car.");
                }
                fetchCars(); // Reload the table after successful deletion
            } catch (error) {
                console.error("Error deleting car:", error.message);
                alert(`Erreur: ${error.message}`);
            }
        }
    };


    // Open popup for editing
    const editCar = (id, model, agency, price) => {
        isEditing = true;
        currentEditId = id;
        carModel.value = model || "";
        carAgency.value = agency || "";
        carPrice.value = price || "";
        popupTitle.textContent = "Modifier la Voiture";
        carFormPopup.classList.remove("hidden");
    };

    // Close the popup
    const closePopup = () => {
        carFormPopup.classList.add("hidden");
        carModel.value = "";
        carAgency.value = "";
        carPrice.value = "";
        popupTitle.textContent = "Ajouter une Voiture";
        isEditing = false;
        currentEditId = null;
    };

    // Open popup for adding
    addCarBtn.addEventListener("click", () => {
        closePopup();
        carFormPopup.classList.remove("hidden");
    });

    // Attach event listeners for dynamically loaded content using event delegation
    carList.addEventListener("click", (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains("modify-btn")) {
            const model = target.dataset.model;
            const agency = target.dataset.agency;
            const price = target.dataset.price;
            editCar(id,model,agency, price);
        }

        if (target.classList.contains("delete-btn")) {
            deleteCar(id);
        }
    });

    // Attach event listeners to buttons
    saveBtn.addEventListener("click", addOrUpdateCar);
    cancelBtn.addEventListener("click", closePopup);

    // Fetch cars on page load
    fetchCars();
});
