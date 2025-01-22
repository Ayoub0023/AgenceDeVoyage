document.addEventListener("DOMContentLoaded", () => {
    const hotelReservationsList = document.getElementById("hotel-reservations-list");
    const flightReservationsList = document.getElementById("flight-reservations-list");
    const carReservationsList = document.getElementById("car-reservations-list");

    const fetchReservations = async () => {
        try {
            // Fetch reservations from the backend
            const response = await fetch("/client/my-reservations");
            if (!response.ok) {
                throw new Error("Failed to fetch reservations");
            }

            const data = await response.json();

            // Handle error from the response if present
            if (data.error) {
                console.error(data.error);
                displayError(data.error);
                return;
            }

            const { hotelReservations, flightReservations, carReservations } = data;

            // Populate hotel reservations
            if (hotelReservations.length > 0) {
                hotelReservations.forEach(reservation => {
                    const reservationCard = createReservationCard(
                        `Hotel ID: ${reservation.hotelId}`,
                        `Check-in: ${reservation.checkInDate}`,
                        `Check-out: ${reservation.checkOutDate}`,
                        `Price: ${reservation.totalPrice} MAD`
                    );
                    hotelReservationsList.appendChild(reservationCard);
                });
            } else {
                hotelReservationsList.innerHTML = "<p>No hotel reservations found.</p>";
            }

            // Populate flight reservations
            if (flightReservations.length > 0) {
                flightReservations.forEach(reservation => {
                    const reservationCard = createReservationCard(
                        `Flight ID: ${reservation.flightId}`,
                        `Booking Date: ${reservation.bookingDateTime}`,
                        `Status: ${reservation.status}`,
                        `Price: ${reservation.totalPrice} MAD`
                    );
                    flightReservationsList.appendChild(reservationCard);
                });
            } else {
                flightReservationsList.innerHTML = "<p>No flight reservations found.</p>";
            }

            // Populate car reservations
            if (carReservations.length > 0) {
                carReservations.forEach(reservation => {
                    const reservationCard = createReservationCard(
                        `Car ID: ${reservation.carId}`,
                        `Rental Start: ${reservation.rentalStartDate}`,
                        `Rental End: ${reservation.rentalEndDate}`,
                        `Price: ${reservation.totalPrice} MAD`
                    );
                    carReservationsList.appendChild(reservationCard);
                });
            } else {
                carReservationsList.innerHTML = "<p>No car reservations found.</p>";
            }
        } catch (error) {
            console.error("Error fetching reservations:", error.message);
            displayError("An error occurred while fetching reservations. Please try again later.");
        }
    };

    // Helper function to create reservation cards
    const createReservationCard = (title, detail1, detail2, price) => {
        const card = document.createElement("div");
        card.className = "reservation-card";
        card.innerHTML = `
            <h3>${title}</h3>
            <p>${detail1}</p>
            <p>${detail2}</p>
            <p><strong>${price}</strong></p>
        `;
        return card;
    };

    // Helper function to display errors
    const displayError = (message) => {
        const errorSection = document.createElement("div");
        errorSection.className = "error-section";
        errorSection.innerHTML = `
            <p class="error-message">${message}</p>
        `;
        document.body.insertBefore(errorSection, document.body.firstChild);
    };

    // Fetch and display reservations
    fetchReservations();
});
