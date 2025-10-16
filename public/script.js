document.addEventListener('DOMContentLoaded', () => {
    const habitContainer = document.getElementById('habit-container');
    const addHabitForm = document.getElementById('add-habit-form');
    const newHabitInput = document.getElementById('new-habit-input');
    const API_URL = 'http://localhost:4567/api';

    // --- Fetch and Display Habits ---
    const fetchHabits = async () => {
        try {
            const response = await fetch(`${API_URL}/habits`);
            if (!response.ok) throw new Error('Network response was not ok');
            const habits = await response.json();
            renderHabits(habits);
        } catch (error) {
            console.error('Failed to fetch habits:', error);
            habitContainer.innerHTML = '<p style="color: red;">Could not load habits. Is the Java server running?</p>';
        }
    };

    // --- Render Habits on the Page ---
    const renderHabits = (habits) => {
        habitContainer.innerHTML = ''; // Clear previous content
        if (habits.length === 0) {
            habitContainer.innerHTML = '<p>No habits yet. Add one to get started!</p>';
        }
        habits.forEach(habit => {
            const habitElement = document.createElement('div');
            habitElement.className = 'habit-card';
            habitElement.innerHTML = `
                <div class="habit-info">
                    <div class="habit-name">${habit.name}</div>
                    <div class="habit-streak">🔥 Streak: ${habit.currentStreak} days</div>
                </div>
                <div class="habit-actions">
                    <button class="complete-btn" data-name="${habit.name}">Complete</button>
                    <button class="delete-btn" data-name="${habit.name}">&#x1F5D1;</button> 
                </div>
            `; // &#x1F5D1; is the wastebasket emoji 🗑️
            habitContainer.appendChild(habitElement);
        });
    };

    // --- Add a New Habit ---
    addHabitForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const habitName = newHabitInput.value.trim();
        if (!habitName) return;

        try {
            const response = await fetch(`${API_URL}/habits`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name: habitName, currentStreak: 0 })
            });
            if (response.status === 409) {
                alert('A habit with this name already exists.');
                return;
            }
            newHabitInput.value = ''; // Clear input
            fetchHabits(); // Refresh the list
        } catch (error) {
            console.error('Failed to add habit:', error);
        }
    });

    // --- Handle Clicks for Completing or Deleting Habits ---
    habitContainer.addEventListener('click', async (e) => {
        const target = e.target;
        const habitName = target.dataset.name;
        if (!habitName) return;

        // Handle Complete Button
        if (target.classList.contains('complete-btn')) {
            try {
                await fetch(`${API_URL}/habits/${encodeURIComponent(habitName)}/complete`, { method: 'POST' });
                fetchHabits(); // Refresh list
            } catch (error) {
                console.error('Failed to complete habit:', error);
            }
        }

        // Handle Delete Button
        if (target.classList.contains('delete-btn')) {
            // Optional: Add a confirmation dialog
            if (confirm(`Are you sure you want to delete the habit "${habitName}"?`)) {
                try {
                    await fetch(`${API_URL}/habits/${encodeURIComponent(habitName)}`, { method: 'DELETE' });
                    fetchHabits(); // Refresh list
                } catch (error) {
                    console.error('Failed to delete habit:', error);
                }
            }
        }
    });

    // Initial fetch of habits when the page loads
    fetchHabits();
});