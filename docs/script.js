// ===== Pawsitive Pet Academy - script.js =====

// 1. COURSES DROP-DOWN (runs on every page)
const courseList = [
  ["Canine Obedience Training", "canine-obedience.html"],
  ["Pet Grooming", "pet-grooming.html"],
  ["Animal Behaviour", "animal-behaviour.html"],
  ["Pet Business Management", "pet-business-management.html"],
  ["Puppy Care", "puppy-care.html"],
  ["Pet First Aid", "pet-first-aid.html"],
  ["Basic Dog Walking", "basic-dog-walking.html"]
];

const coursesLink = document.querySelector('nav a[href="courses.html"]');

if (coursesLink) {
  const dropdown = document.createElement("div");
  dropdown.className = "dropdown";
  coursesLink.parentNode.insertBefore(dropdown, coursesLink);
  dropdown.appendChild(coursesLink);
  coursesLink.textContent = "COURSES ▾";

  const menu = document.createElement("div");
  menu.className = "dropdown-menu";
  for (let i = 0; i < courseList.length; i++) {
    const link = document.createElement("a");
    link.textContent = courseList[i][0];
    link.href = courseList[i][1];
    menu.appendChild(link);
  }
  dropdown.appendChild(menu);
}

// HELPER: show errors or results under a form
function showMessage(form, lines, isError) {
  let box = form.querySelector(".form-message");
  if (!box) {
    box = document.createElement("div");
    box.className = "form-message";
    form.appendChild(box);
  }
  box.className = isError ? "form-message error" : "form-message success";
  box.innerHTML = "";
  const list = document.createElement("ul");
  for (let i = 0; i < lines.length; i++) {
    const item = document.createElement("li");
    item.textContent = lines[i];
    list.appendChild(item);
  }
  box.appendChild(list);
}

function formatRand(amount) {
  return "R" + amount.toFixed(2);
}

// 2. FEES CALCULATOR (fees.html only)
const calculateButton = document.getElementById("btn-calculate");

if (calculateButton) {
  calculateButton.addEventListener("click", function () {
    const form = document.getElementById("fee-calculator-form");
    const totalOutput = document.getElementById("total-amount");
    const name = document.getElementById("calc-name").value.trim();
    const phone = document.getElementById("calc-phone").value.replace(/\s/g, "");
    const email = document.getElementById("calc-email").value.trim();

    // Put every ticked course into an array
    const selectedCourses = [];
    const ticked = document.querySelectorAll('input[name="course"]:checked');
    for (let i = 0; i < ticked.length; i++) {
      const label = document.querySelector('label[for="' + ticked[i].id + '"]');
      selectedCourses.push({
        name: label.textContent.split(" – ")[0],
        price: Number(ticked[i].value)
      });
    }

    // Error handling
    const errors = [];
    if (name === "") {
      errors.push("Please enter your full name.");
    }
    if (!/^0\d{9}$/.test(phone)) {
      errors.push("Please enter a valid 10-digit phone number, e.g. 0821234567.");
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      errors.push("Please enter a valid email address.");
    }
    if (selectedCourses.length === 0) {
      errors.push("Please select at least one course.");
    }
    if (errors.length > 0) {
      totalOutput.textContent = "R0";
      showMessage(form, errors, true);
      return;
    }

    // Step 1: add up the course fees
    let subtotal = 0;
    for (let i = 0; i < selectedCourses.length; i++) {
      subtotal += selectedCourses[i].price;
    }

    // Step 2: work out the discount
    let discountRate = 0;
    if (selectedCourses.length === 2) {
      discountRate = 0.05;
    } else if (selectedCourses.length === 3) {
      discountRate = 0.10;
    } else if (selectedCourses.length >= 4) {
      discountRate = 0.15;
    }
    const discount = subtotal * discountRate;
    const afterDiscount = subtotal - discount;

    // Step 3: add 15% VAT after the discount
    const vat = afterDiscount * 0.15;
    const total = afterDiscount + vat;

    // Step 4: show the quote
    totalOutput.textContent = formatRand(total);
    const quote = ["Quote for " + name + ":"];
    for (let i = 0; i < selectedCourses.length; i++) {
      quote.push(selectedCourses[i].name + ": " + formatRand(selectedCourses[i].price));
    }
    quote.push("Subtotal: " + formatRand(subtotal));
    quote.push("Discount (" + discountRate * 100 + "%): -" + formatRand(discount));
    quote.push("VAT (15%): " + formatRand(vat));
    quote.push("Total: " + formatRand(total));
    quote.push("A consultant will contact you on " + phone + " or " + email + ".");
    showMessage(form, quote, false);
  });
}

// 3. CONTACT FORM VALIDATION (contact.html only)
const contactForm = document.querySelector(".contact-form");

if (contactForm) {
  contactForm.addEventListener("submit", function (event) {
    event.preventDefault();
    const name = document.getElementById("contact-name").value.trim();
    const email = document.getElementById("contact-email").value.trim();
    const message = document.getElementById("contact-message").value.trim();

    const errors = [];
    if (name === "") {
      errors.push("Please enter your name.");
    }
    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
      errors.push("Please enter a valid email address.");
    }
    if (message.length < 10) {
      errors.push("Please write a message of at least 10 characters.");
    }
    if (errors.length > 0) {
      showMessage(contactForm, errors, true);
      return;
    }
    showMessage(contactForm, ["Thank you, " + name + "! We'll reply to " + email + " soon."], false);
    contactForm.reset();
  });
}