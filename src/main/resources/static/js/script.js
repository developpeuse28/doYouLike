function validateForm() {
    const memberId = document.getElementById('memberId').value;
    const password = document.getElementById('password').value;

    if (memberId.trim() === '' || password.trim() === '') {
        alert('Please fill in all the required fields.');
        return false;
    }

    return true;
}

const loginErrorDiv = document.getElementById("login-error-message");
if (loginErrorDiv && loginErrorDiv.innerText.trim() !== "") {
    alert(loginErrorDiv.innerText);
    loginErrorDiv.innerText = "";
}

function validateSignUpForm() {
    const memberId = document.getElementById('memberId').value;
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    const name = document.getElementById('name').value;
    const gender = document.getElementById('gender').value;
    const email = document.getElementById('email').value;
    const birthYear = document.getElementById('birthYear').value;
    const major = document.getElementById('major').value;

    if (memberId.trim() === '' || password.trim() === '' || confirmPassword.trim() === '' || name.trim() === '' ||
        gender.trim() === '' || email.trim() === '' || birthYear.trim() === '' || major.trim() === '') {
        alert('Please fill in all the required fields.');
        return false;
    }

    if (!isPasswordValid(password)) {
        alert(
            'Password must be at least 8 characters long and contain a combination of letters, numbers, and symbols (@ $ ! % * # ? &).'
        );
        return false;
    }

    if (password !== confirmPassword) {
        alert('Passwords do not match.');
        return false;
    }

    return true;
}


function isPasswordValid(password) {
    if (password.length < 8) {
        return false;
    }

    const letterRegex = /[a-zA-Z]/;
    const numberRegex = /[0-9]/;
    const symbolRegex = /[@$!%*#?&]/;

    if (!letterRegex.test(password) || !numberRegex.test(password) || !symbolRegex.test(password)) {
        return false;
    }

    return true;
}


function togglePasswordVisibility(event) {
    const target = event.target;
    const passwordInput = document.getElementById("password");
    const eyeIcon = document.getElementById("eye-icon");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const cEyeIcon = document.getElementById("cEye-icon");

    if (target === eyeIcon) {
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
            eyeIcon.classList.add("show-password");
        } else {
            passwordInput.type = "password";
            eyeIcon.classList.remove("show-password");
        }
    } else if (target === cEyeIcon) {
        if (confirmPasswordInput.type === "password") {
            confirmPasswordInput.type = "text";
            cEyeIcon.classList.add("show-password");
        } else {
            confirmPasswordInput.type = "password";
            cEyeIcon.classList.remove("show-password");
        }
    }
}



const idCheck = () => {
    const id = document.getElementById("memberId").value;
    const checkResult = document.getElementById("check-result");
    const signupButton = document.getElementById("signup-button");
    console.log("value: ", id);
    $.ajax({
        type: "post",
        url: "/member/id-check",
        data: {
            "memberId": id
        },
        success: function(res) {
            console.log("success", res);
            if (res == "ok") {
                console.log("this ID is usable");
                checkResult.style.color = "green";
                checkResult.innerHTML = "This ID is usable";
                signupButton.disabled = false;
            } else {
                console.log("this id is already being used");
                checkResult.style.color = "red";
                checkResult.innerHTML = "This ID is already being used";
                signupButton.disabled = true;
            }
        },
        error: function(err) {
            console.log("error occurred", err);
        }
    });
};


const profileMenu = document.querySelector(".profile-menu");
const dropdownContent = document.querySelector(".dropdown-content");
if (profileMenu && dropdownContent) {
    profileMenu.addEventListener("click", function () {
        dropdownContent.classList.toggle("show");
    });
}

window.addEventListener("click", function (event) {
    if (!event.target.matches(".profile-menu")) {
        const dropdowns = document.getElementsByClassName("dropdown-content");
        for (let i = 0; i < dropdowns.length; i++) {
            const openDropdown = dropdowns[i];
            if (openDropdown.classList.contains("show")) {
                openDropdown.classList.remove("show");
            }
        }
    }
});

const emojiDropdown = document.querySelector('.emoji-dropdown-content');
const selectedEmojiField = document.getElementById('selectedEmoji');
const profileEmoji = document.querySelector('.profile-emoji');

if (emojiDropdown) {
    emojiDropdown.addEventListener('click', function(event) {
        if (event.target.classList.contains('emoji-option')) {
            const selectedEmoji = event.target.innerText;
            profileEmoji.innerText = selectedEmoji;
            selectedEmojiField.value = selectedEmoji;
        }
    });
}

function selectEmoji(emoji) {
    console.log("Emoji selected:", emoji);
    const emojiInput = document.querySelector('input[name="status"]');
    emojiInput.value = emoji;
}

const menuLinks = document.querySelectorAll('.menu-link');
const modals = document.querySelectorAll('.modal');

function openPopup(popupId) {
    document.getElementById(popupId).style.display = 'block';
    document.getElementById('overlay').style.display = 'block';
}

function closePopup(popupId) {
    document.getElementById(popupId).style.display = 'none';
    document.getElementById('overlay').style.display = 'none';
}

function confirmDelete() {
    const confirmation = confirm("Are you sure you want to delete your account?");
    if (confirmation) {
        if (memberId) {
            window.location.href = '/member/delete/' + memberId;
        }
    }
}

const openButton = document.getElementById('open-message-popup');
const closeButton = document.getElementById('close-message-popup');
const cancelButton = document.getElementById('cancel-message-popup');
const popup = document.getElementById('message-popup');

if (openButton && popup) {
    openButton.addEventListener('click', () => {
        popup.style.display = 'block';
    });
}

if (openButton && closeButton && popup) {
    openButton.addEventListener('click', () => {
        popup.style.display = 'block';
    });

    closeButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });
}

if (openButton && closeButton && cancelButton && popup) {
    openButton.addEventListener('click', () => {
        popup.style.display = 'block';
    });

    closeButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });

    cancelButton.addEventListener('click', () => {
        popup.style.display = 'none';
    });
}

function savePreferences() {
    const preferences = {
        ageRangeMin: document.getElementById('ageRangeMin').value,
        ageRangeMax: document.getElementById('ageRangeMax').value,
        dormDepartment: document.getElementById('dormDepartment').value,
        roomType: document.getElementById('roomType').value,
        lifestyle1: document.getElementById('lifestyle1').value,
        lifestyle2: document.getElementById('lifestyle2').value,
        lifestyle3: document.getElementById('lifestyle3').value,
        sleepHabit1: document.getElementById('sleepHabit1').checked ? 'SLEEP_HABIT1_DONT_SNORE' : '',
        sleepHabit2: document.getElementById('sleepHabit2').checked ? 'SLEEP_HABIT2_DONT_GRIND_TEETH' : '',
        sleepHabit3: document.getElementById('sleepHabit3').checked ? 'SLEEP_HABIT3_DONT_SLEEP_TALK_OR_SLEEPWALK' : '',
    };

    localStorage.setItem('preferences', JSON.stringify(preferences));
}

function loadPreferences() {
    const preferences = JSON.parse(localStorage.getItem('preferences'));

    if (preferences) {
        document.getElementById('ageRangeMin').value = preferences.ageRangeMin || 18;
        document.getElementById('ageRangeMax').value = preferences.ageRangeMax || 50;
        document.getElementById('dormDepartment').value = preferences.dormDepartment || '';
        document.getElementById('roomType').value = preferences.roomType || '';
        document.getElementById('lifestyle1').value = preferences.lifestyle1 || '';
        document.getElementById('lifestyle2').value = preferences.lifestyle2 || '';
        document.getElementById('lifestyle3').value = preferences.lifestyle3 || '';
        document.getElementById('sleepHabit1').checked = preferences.sleepHabit1 === 'SLEEP_HABIT1_DONT_SNORE';
        document.getElementById('sleepHabit2').checked = preferences.sleepHabit2 === 'SLEEP_HABIT2_DONT_GRIND_TEETH';
        document.getElementById('sleepHabit3').checked = preferences.sleepHabit3 === 'SLEEP_HABIT3_DONT_SLEEP_TALK_OR_SLEEPWALK';
    }
}

document.getElementById('preferencesForm').addEventListener('submit', function (event) {
    event.preventDefault();
    savePreferences();
});

window.addEventListener('load', function () {
    loadPreferences();
});

document.addEventListener("DOMContentLoaded", function () {
    const memberList = document.querySelector(".member-list");
    const prevPageButton = document.getElementById("prevPageButton");
    const nextPageButton = document.getElementById("nextPageButton");

    const membersPerPage = 5;
    let currentPage = 0;

    const showPage = (page) => {
        const startIndex = page * membersPerPage;
        const endIndex = startIndex + membersPerPage;
        const allMembers = Array.from(memberList.querySelectorAll("li"));

        allMembers.forEach((member, index) => {
            if (index >= startIndex && index < endIndex) {
                member.style.display = "block";
            } else {
                member.style.display = "none";
            }
        });
    };

    const updatePaginationButtons = () => {
        prevPageButton.disabled = currentPage === 0;
        nextPageButton.disabled =
            (currentPage + 1) * membersPerPage >= memberList.children.length;
    };

    const updatePagination = () => {
        showPage(currentPage);
        updatePaginationButtons();
        filterMembers();
    };

    prevPageButton.addEventListener("click", () => {
        if (currentPage > 0) {
            currentPage--;
            updatePagination();
        }
    });

    nextPageButton.addEventListener("click", () => {
        const totalPages = Math.ceil(memberList.children.length / membersPerPage);
        if (currentPage < totalPages - 1) {
            currentPage++;
            updatePagination();
        }
    });
    updatePagination();
});

function calculateAge(birthYear) {
    const currentYear = new Date().getFullYear();
    return currentYear - birthYear + 1;
}

function filterMembers() {
    const preferences = JSON.parse(localStorage.getItem('preferences'));
    const memberList = document.querySelector(".member-list");
    const allMembers = Array.from(memberList.querySelectorAll("li"));

    allMembers.forEach((member) => {
        const age = calculateAge(parseInt(member.querySelector(".data-birth-year").value));
        const dormDepartment = member.querySelector(".data-dorm-department").value;
        const roomType = member.querySelector(".data-room-type").value;
        const lifestyle1 = member.querySelector(".data-lifestyle1").value;
        const lifestyle2 = member.querySelector(".data-lifestyle2").value;
        const lifestyle3 = member.querySelector(".data-lifestyle3").value;
        const sleepHabit1 = member.querySelector(".data-sleep-habit1").value;
        const sleepHabit2 = member.querySelector(".data-sleep-habit2").value;
        const sleepHabit3 = member.querySelector(".data-sleep-habit3").value;

        const memberAttributes = {
            age,
            dormDepartment,
            roomType,
            lifestyle1,
            lifestyle2,
            lifestyle3,
            sleepHabit1,
            sleepHabit2,
            sleepHabit3
        };

        const memberAttributesString = JSON.stringify(memberAttributes, null, 2);
        console.log("Member Attributes:", memberAttributesString);

        const ageInRange = age >= preferences.ageRangeMin && age <= preferences.ageRangeMax;
        const dormMatches = preferences.dormDepartment === '' || dormDepartment === preferences.dormDepartment;
        const roomTypeMatches = preferences.roomType === '' || roomType === preferences.roomType;
        const lifestyle1Matches = preferences.lifestyle1 === '' || lifestyle1 === preferences.lifestyle1;
        const lifestyle2Matches = preferences.lifestyle2 === '' || lifestyle2 === preferences.lifestyle2;
        const lifestyle3Matches = preferences.lifestyle3 === '' || lifestyle3 === preferences.lifestyle3;
        const sleepHabit1Matches = preferences.sleepHabit1 === '' || sleepHabit1 === preferences.sleepHabit1;
        const sleepHabit2Matches = preferences.sleepHabit2 === '' || sleepHabit2 === preferences.sleepHabit2;
        const sleepHabit3Matches = preferences.sleepHabit3 === '' || sleepHabit3 === preferences.sleepHabit3;

        if (
            ageInRange &&
            dormMatches &&
            roomTypeMatches &&
            lifestyle1Matches &&
            lifestyle2Matches &&
            lifestyle3Matches &&
            sleepHabit1Matches &&
            sleepHabit2Matches &&
            sleepHabit3Matches
        ) {
            console.log("Member matched preferences:", member);
            member.style.display = "block";
        } else {
            console.log("Member did not match preferences:", member);
            member.style.display = "none";
        }
    });
}

document.getElementById('searchButton').addEventListener('click', function (event) {
    event.preventDefault();
    savePreferences();
    filterMembers();
});


function goBack() {
    window.history.back();
}