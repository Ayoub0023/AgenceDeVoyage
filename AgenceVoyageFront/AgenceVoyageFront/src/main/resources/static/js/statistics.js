document.addEventListener("DOMContentLoaded", () => {
    // Fetch gains data
    const dailyGains = parseFloat(document.querySelector('[th\\:text="${dailyGains}"]').textContent.split(' ')[0]) || 0;
    const monthlyGains = parseFloat(document.querySelector('[th\\:text="${monthlyGains}"]').textContent.split(' ')[0]) || 0;
    const yearlyGains = parseFloat(document.querySelector('[th\\:text="${yearlyGains}"]').textContent.split(' ')[0]) || 0;

    // Daily Gains Chart
    const dailyGainsChartCtx = document.getElementById("dailyGainsChart").getContext("2d");
    new Chart(dailyGainsChartCtx, {
        type: "bar",
        data: {
            labels: ["Aujourd'hui"],
            datasets: [{
                label: "Gains (MAD)",
                data: [dailyGains],
                backgroundColor: "rgba(54, 162, 235, 0.6)"
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: "Gains Aujourd'hui"
                }
            }
        }
    });

    // Monthly Gains Chart
    const monthlyGainsChartCtx = document.getElementById("monthlyGainsChart").getContext("2d");
    new Chart(monthlyGainsChartCtx, {
        type: "line",
        data: {
            labels: ["Ce Mois"],
            datasets: [{
                label: "Gains (MAD)",
                data: [monthlyGains],
                borderColor: "rgba(75, 192, 192, 1)",
                fill: false
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: "Gains Mensuels"
                }
            }
        }
    });

    // Yearly Gains Chart
    const yearlyGainsChartCtx = document.getElementById("yearlyGainsChart").getContext("2d");
    new Chart(yearlyGainsChartCtx, {
        type: "pie",
        data: {
            labels: ["Cette Année"],
            datasets: [{
                label: "Gains (MAD)",
                data: [yearlyGains],
                backgroundColor: [
                    "rgba(255, 99, 132, 0.6)",
                    "rgba(54, 162, 235, 0.6)"
                ]
            }]
        },
        options: {
            responsive: true,
            plugins: {
                title: {
                    display: true,
                    text: "Gains Annuels"
                }
            }
        }
    });
});
