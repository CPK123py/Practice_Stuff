console.log('Welcome to the Community Portal');
window.addEventListener('load', () => alert('Page loaded - Portal ready!'));

const eventData = [
    { id: 1, name: 'Harvest Festival', date: '2026-09-15', price: 10, seats: 50, category: 'festival' },
    { id: 2, name: 'Summer Concert Series', date: '2026-07-20', price: 5, seats: 0, category: 'music' },
    { id: 3, name: 'Founders Day Gala', date: '2026-06-10', price: 20, seats: 25, category: 'gala' }
];

const prices = { harvest: 10, concert: 5, gala: 20 };

class Event {
    constructor(id, name, date, price, seats, category) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.price = price;
        this.seats = seats;
        this.category = category;
    }

    checkAvailability() {
        return this.seats > 0;
    }

    getDetails() {
        return `${this.name} - $${this.price} (${this.seats} seats available)`;
    }
}

Event.prototype.getSeatCount = function() {
    return this.seats;
};

const registrations = [];
let totalRegistrationsInSession = 0;

const createCategoryCounter = (category) => {
    let count = 0;
    return {
        increment: () => ++count,
        getCount: () => count,
        reset: () => count = 0
    };
};

const musicCounter = createCategoryCounter('music');
const festivalCounter = createCategoryCounter('festival');
const galaCounter = createCategoryCounter('gala');

function getAvailableEvents() {
    return eventData.filter(event => event.checkAvailability());
}

function formatEventCard(event) {
    return `<div class="event-item">${event.name} - $${event.price} (${event.seats} seats)</div>`;
}

function filterEventsByCategory(category, callback) {
    const filtered = eventData.filter(e => e.category === category);
    if (callback) callback(filtered);
    return filtered;
}

function calculatePrice() {
    const event = document.getElementById('event').value;
    const tickets = parseInt(document.getElementById('ticketCount').value) || 0;
    const total = (prices[event] || 0) * tickets;
    document.getElementById('totalPrice').textContent = total;
}

function validateRegistrationForm() {
    const name = document.getElementById('name').value.trim();
    const email = document.getElementById('email').value.trim();
    const event = document.getElementById('event').value;

    if (!name || name.length < 3) {
        throw new Error('Name must be at least 3 characters');
    }
    if (!email || !email.includes('@')) {
        throw new Error('Invalid email format');
    }
    if (!event) {
        throw new Error('Please select an event');
    }

    return { name, email, event };
}

function handleRegistration(e) {
    e.preventDefault();
    
    try {
        const { name, email, event } = validateRegistrationForm();
        
        const registrationData = {
            id: registrations.length + 1,
            name,
            email,
            event,
            timestamp: new Date().toLocaleString()
        };

        registrations.push(registrationData);
        totalRegistrationsInSession++;

        const msg = document.getElementById('regMessage');
        msg.className = 'message success';
        msg.textContent = `Thank you for registering, ${name}!`;

        console.log('Registration data:', registrationData);
        sendRegistrationToServer(registrationData);

        setTimeout(() => msg.className = 'message', 4000);
        document.querySelector('form').reset();
        
    } catch (error) {
        const msg = document.getElementById('regMessage');
        msg.className = 'message error';
        msg.textContent = `Error: ${error.message}`;
        console.error('Registration error:', error);
    }
}

function sendRegistrationToServer(data) {
    const payload = JSON.stringify(data);
    
    fetch('https://jsonplaceholder.typicode.com/posts', {
        method: 'POST',
        body: payload,
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => {
        if (!response.ok) throw new Error('Server error');
        return response.json();
    })
    .then(result => {
        console.log('Server response:', result);
        updateDebugOutput('Registration sent to server');
    })
    .catch(error => {
        console.error('Fetch error:', error);
    });
}

function sendRegistrationAsync(data) {
    (async () => {
        try {
            const response = await fetch('https://jsonplaceholder.typicode.com/posts', {
                method: 'POST',
                body: JSON.stringify(data),
                headers: { 'Content-Type': 'application/json' }
            });

            if (!response.ok) throw new Error('Server response error');
            const result = await response.json();
            console.log('Async registration result:', result);
        } catch (error) {
            console.error('Async registration error:', error);
        }
    })();
}

function updateRating() {
    document.getElementById('ratingValue').textContent = document.getElementById('rating').value;
}

function handleFeedback(e) {
    e.preventDefault();
    
    const feedbackEvent = document.getElementById('feedbackEvent').value;
    const rating = document.getElementById('rating').value;
    const review = document.getElementById('review').value;

    const feedbackData = { feedbackEvent, rating, review, timestamp: new Date().toLocaleString() };

    const msg = document.getElementById('feedbackMessage');
    msg.className = 'message success';
    msg.textContent = 'Thank you for your feedback!';

    console.log('Feedback submitted:', feedbackData);
    setTimeout(() => msg.className = 'message', 4000);
    e.target.reset();
}

function savePreference() {
    const eventType = document.getElementById('eventType').value;
    
    if (eventType) {
        localStorage.setItem('preferredEvent', eventType);
        sessionStorage.setItem('lastSelected', eventType);
        showPrefMessage('Preference saved!', 'success');
    } else {
        showPrefMessage('Please select an event', 'error');
    }
}

function clearPreferences() {
    localStorage.clear();
    sessionStorage.clear();
    document.getElementById('eventType').value = '';
    showPrefMessage('All preferences cleared', 'success');
}

function loadPreference() {
    const saved = localStorage.getItem('preferredEvent');
    if (saved) {
        document.getElementById('eventType').value = saved;
    }
}

function showPrefMessage(text, type) {
    const msg = document.getElementById('prefMessage');
    msg.className = `message ${type}`;
    msg.textContent = text;
    setTimeout(() => msg.className = 'message', 3000);
}

function scrollToSection(id) {
    const element = document.getElementById(id);
    if (element) {
        element.scrollIntoView({ behavior: 'smooth' });
    }
}

function findNearbyEvents() {
    const resultDiv = document.getElementById('geoResult');
    const btn = document.getElementById('geoBtn');
    
    if (!navigator.geolocation) {
        resultDiv.innerHTML = '<div class="message error">Geolocation not supported</div>';
        return;
    }

    resultDiv.innerHTML = '<div class="message info">Getting your location...</div>';
    btn.disabled = true;

    navigator.geolocation.getCurrentPosition(
        (position) => {
            const { latitude, longitude, accuracy } = position.coords;
            displayNearbyEvents(latitude, longitude, accuracy);
            btn.disabled = false;
        },
        (error) => {
            let message = 'Error getting location';
            if (error.code === error.PERMISSION_DENIED) {
                message = 'Permission denied. Enable location access.';
            } else if (error.code === error.TIMEOUT) {
                message = 'Request timeout.';
            }
            resultDiv.innerHTML = `<div class="message error">${message}</div>`;
            btn.disabled = false;
        },
        { enableHighAccuracy: true, timeout: 10000, maximumAge: 0 }
    );
}

function displayNearbyEvents(lat, lon, accuracy) {
    const resultDiv = document.getElementById('geoResult');
    let html = `<div class="message success">
        <strong>Your Location:</strong>
        <div class="coords">
            Latitude: ${lat.toFixed(6)}<br>
            Longitude: ${lon.toFixed(6)}<br>
            Accuracy: ±${accuracy.toFixed(2)}m
        </div>
        <strong>Nearby Events:</strong>`;
    
    eventData.forEach(event => {
        const distance = calculateDistance(lat, lon, 40.2206, -85.1502);
        html += `<div class="event-item">${event.name} - $${event.price}<br><small>${distance.toFixed(1)}km away</small></div>`;
    });
    
    html += '</div>';
    resultDiv.innerHTML = html;
}

function calculateDistance(lat1, lon1, lat2, lon2) {
    const R = 6371;
    const dLat = (lat2 - lat1) * Math.PI / 180;
    const dLon = (lon2 - lon1) * Math.PI / 180;
    const a = Math.sin(dLat/2) * Math.sin(dLat/2) +
              Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
              Math.sin(dLon/2) * Math.sin(dLon/2);
    return 6371 * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
}

function logEventData() {
    console.log('=== Event Data ===');
    console.table(eventData);
    
    console.log('=== Registrations ===');
    console.table(registrations);
    
    const eventEntries = Object.entries(eventData[0]);
    console.log('Event object entries:', eventEntries);
    
    updateDebugOutput('Event data logged to console');
}

function triggerError() {
    try {
        const availableEvents = getAvailableEvents();
        const totalRevenue = availableEvents
            .map(e => e.price)
            .reduce((sum, price) => sum + price, 0);
        
        console.log('Total revenue potential: $' + totalRevenue);
        updateDebugOutput('Calculation completed: $' + totalRevenue);
    } catch(e) {
        console.error('Error:', e.message);
        updateDebugOutput('Error triggered - check console');
    }
}

function performCalculation() {
    console.log('Starting calculation...');
    
    const total = eventData.reduce((sum, e) => sum + e.price, 0);
    const avgPrice = total / eventData.length;
    const eventNames = eventData.map(e => e.name);
    
    console.log('Total: $' + total);
    console.log('Average: $' + avgPrice.toFixed(2));
    console.log('Event names:', eventNames);
    
    updateDebugOutput(`Total: $${total} | Average: $${avgPrice.toFixed(2)}`);
}

function monitorLoop() {
    console.group('Event Processing');
    
    eventData.forEach((e, i) => {
        console.log(`[${i}] ${e.name}: $${e.price} (${e.seats} seats)`);
    });
    
    console.groupEnd();
    
    console.group('Destructuring Example');
    eventData.forEach(({ name, price, category }) => {
        console.log(`${name} (${category}): $${price}`);
    });
    console.groupEnd();
    
    updateDebugOutput('Loop monitoring - check console');
}

function updateDebugOutput(text) {
    const debugDiv = document.getElementById('debugOutput');
    if (debugDiv) {
        debugDiv.innerHTML = '<strong>Last Action:</strong> ' + text;
    }
}

function initializeEventHandlers() {
    const regForm = document.querySelector('form[onsubmit*="handleRegistration"]');
    if (regForm) {
        regForm.addEventListener('submit', handleRegistration);
    }

    const feedbackForm = document.querySelector('form[onsubmit*="handleFeedback"]');
    if (feedbackForm) {
        feedbackForm.addEventListener('submit', handleFeedback);
    }

    const eventSelect = document.getElementById('event');
    if (eventSelect) {
        eventSelect.addEventListener('change', calculatePrice);
    }

    const ticketCount = document.getElementById('ticketCount');
    if (ticketCount) {
        ticketCount.addEventListener('change', calculatePrice);
    }

    const ratingSlider = document.getElementById('rating');
    if (ratingSlider) {
        ratingSlider.addEventListener('input', updateRating);
    }

    document.addEventListener('keydown', (e) => {
        if (e.key === '?') {
            console.log('Portal Help:', {
                shortcuts: { '?': 'Show help' },
                functions: ['logEventData()', 'triggerError()', 'performCalculation()', 'monitorLoop()']
            });
        }
    });
}

function displayFilteredEvents(category) {
    const filtered = filterEventsByCategory(category, (events) => {
        console.log(`Filtered ${category} events:`, events);
    });
    return filtered;
}

function cloneEventList() {
    const cloned = [...eventData];
    return cloned;
}

function getEventSummary() {
    const summary = eventData.map(({ name, price, seats }) => ({
        name,
        price,
        seats,
        available: seats > 0
    }));
    return summary;
}

function initializePortal() {
    console.log('Portal initializing...');
    
    loadPreference();
    initializeEventHandlers();
    
    const availableCount = getAvailableEvents().length;
    console.log(`Available events: ${availableCount}/${eventData.length}`);
    
    const summary = getEventSummary();
    console.log('Event summary:', summary);
    
    console.log('Portal ready!');
}

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initializePortal);
} else {
    initializePortal();
}

window.PortalAPI = {
    getEvents: () => eventData,
    getRegistrations: () => registrations,
    getTotalRegistrations: () => totalRegistrationsInSession,
    filterByCategory: filterEventsByCategory,
    getEventSummary,
    cloneEventList,
    displayFilteredEvents
};
