document.addEventListener("DOMContentLoaded", () => {
    const flightList = document.getElementById("flightList");
    const addFlightBtn = document.getElementById("addFlightBtn");
    const flightFormPopup = document.getElementById("flightFormPopup");
    const saveBtn = document.getElementById("saveBtn");
    const cancelBtn = document.getElementById("cancelBtn");

    // Form fields
    const airlineInput = document.getElementById("airline");
    const arrivalInput = document.getElementById("arrival");
    const departureInput = document.getElementById("departure");
    const departureDateInput = document.getElementById("departureDate");
    const departureTimeInput = document.getElementById("departureTime");
    const arrivalDateInput = document.getElementById("arrivalDate");
    const arrivalTimeInput = document.getElementById("arrivalTime");
    const priceInput = document.getElementById("price");
    const popupTitle = document.getElementById("popupTitle");

    let isEditing = false;
    let currentEditId = null;

    // Hide the popup on page load
    flightFormPopup.classList.add("hidden");

    // Fetch and display flights
    const fetchFlights = async () => {
        try {
            const response = await fetch("/admin/flights");
            if (!response.ok) throw new Error("Failed to fetch flights.");
            const flights = await response.json();
            flightList.innerHTML = flights
                .map(
                    (flight) => `
                    <tr>
                        <td>${flight.id}</td>
                        <td>${flight.airline}</td>
                        <td>${flight.departure}</td>
                        <td>${flight.arrival}</td>
                        <td>${flight.departureDate}</td>
                        <td>${flight.departureTime}</td>
                        <td>${flight.arrivalDate}</td>
                        <td>${flight.arrivalTime}</td>
                        <td>${flight.price.toFixed(2)} MAD</td>
                        <td>
                            <div class="action-buttons">
                                <button 
                                    class="action-btn modify-btn" 
                                    data-id="${flight.id}" 
                                    data-airline="${flight.airline}" 
                                    data-departure="${flight.departure}" 
                                    data-departuredate="${flight.departureDate}"
                                    data-departuretime="${flight.departureTime}"
                                    data-arrival="${flight.arrival}" 
                                    data-arrivaldate="${flight.arrivalDate}"
                                    data-arrivaltime="${flight.arrivalTime}"
                                    data-price="${flight.price}">
                                    ✏️
                                </button>
                                <button class="action-btn delete-btn" data-id="${flight.id}">🗑️</button>
                            </div>
                        </td>
                    </tr>`
                )
                .join("");
        } catch (error) {
            console.error("Error fetching flights:", error.message);
        }
    };

    // Add or update a flight
    const addOrUpdateFlight = async () => {
        const payload = {
            airline: airlineInput.value.trim(),
            departure: departureInput.value.trim(),
            departureDate: departureDateInput.value.trim(),
            departureTime: departureTimeInput.value.trim(),
            arrival: arrivalInput.value.trim(),
            arrivalDate: arrivalDateInput.value.trim(),
            arrivalTime: arrivalTimeInput.value.trim(),
            price: parseFloat(priceInput.value),
        };

        if (
            !payload.airline ||
            !payload.departure ||
            !payload.departureDate ||
            !payload.departureTime ||
            !payload.arrival ||
            !payload.arrivalDate ||
            !payload.arrivalTime ||
            isNaN(payload.price)
        ) {
            alert("Tous les champs sont obligatoires.");
            return;
        }

        const method = isEditing ? "PUT" : "POST";
        const url = isEditing
            ? `/admin/flights/${currentEditId}`
            : `/admin/flights`;

        try {
            const response = await fetch(url, {
                method,
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload),
            });

            if (!response.ok) {
                const errorData = await response.json();
                throw new Error(errorData.message || "Failed to save flight.");
            }

            fetchFlights(); // Reload
            closePopup(); // Close the form
        } catch (error) {
            console.error("Error saving flight:", error.message);
            alert(`Erreur: ${error.message}`);
        }
    };

    // Delete a flight
    const deleteFlight = async (id) => {
        if (confirm("Êtes-vous sûr de vouloir supprimer ce vol ?")) {
            try {
                const response = await fetch(`/admin/flights/${id}`, {
                    method: "DELETE",
                });
                if (!response.ok) {
                    const errorData = await response.json();
                    throw new Error(
                        errorData.message || "Failed to delete flight."
                    );
                }
                fetchFlights(); // Reload the table after successful deletion
            } catch (error) {
                console.error("Error deleting flight:", error.message);
                alert(`Erreur: ${error.message}`);
            }
        }
    };

    // Open popup for editing
    const editFlight = (
        id,
        airline,
        departure,
        departureDate,
        departureTime,
        arrival,
        arrivalDate,
        arrivalTime,
        price
    ) => {
        isEditing = true;
        currentEditId = id;
        airlineInput.value = airline || "";
        departureInput.value = departure || "";
        departureDateInput.value = departureDate || "";
        departureTimeInput.value = departureTime || "";
        arrivalInput.value = arrival || "";
        arrivalDateInput.value = arrivalDate || "";
        arrivalTimeInput.value = arrivalTime || "";
        priceInput.value = price || "";
        popupTitle.textContent = "Modifier le Vol";
        flightFormPopup.classList.remove("hidden");
    };

    // Close the popup
    const closePopup = () => {
        flightFormPopup.classList.add("hidden");
        airlineInput.value = "";
        departureInput.value = "";
        departureDateInput.value = "";
        departureTimeInput.value = "";
        arrivalInput.value = "";
        arrivalDateInput.value = "";
        arrivalTimeInput.value = "";
        priceInput.value = "";
        popupTitle.textContent = "Ajouter un Vol";
        isEditing = false;
        currentEditId = null;
    };

    // Open popup for adding
    addFlightBtn.addEventListener("click", () => {
        closePopup();
        flightFormPopup.classList.remove("hidden");
    });

    // Attach event listeners for dynamically loaded content using event delegation
    flightList.addEventListener("click", (event) => {
        const target = event.target;
        const id = target.dataset.id;

        if (target.classList.contains("modify-btn")) {
            const airline = target.dataset.airline;
            const departure = target.dataset.departure;
            const departureDate = target.dataset.departuredate;
            const departureTime = target.dataset.departuretime;
            const arrival = target.dataset.arrival;
            const arrivalDate = target.dataset.arrivaldate;
            const arrivalTime = target.dataset.arrivaltime;
            const price = target.dataset.price;
            editFlight(
                id,
                airline,
                departure,
                departureDate,
                departureTime,
                arrival,
                arrivalDate,
                arrivalTime,
                price
            );
        }

        if (target.classList.contains("delete-btn")) {
            deleteFlight(id);
        }
    });

    // Attach event listeners to buttons
    saveBtn.addEventListener("click", addOrUpdateFlight);
    cancelBtn.addEventListener("click", closePopup);

    // Fetch flights on page load
    fetchFlights();
});