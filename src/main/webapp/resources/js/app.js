document.addEventListener("DOMContentLoaded", function () {

    /**
     * spring mvc form produces hidden input field for checkboxes
     * <input type="hidden" value = "on" name = "_categories"/>
     * that messes with css for <span class = "checkbox">
     * @type {NodeListOf<Element>}
     */
    let springMVCTrash = document.querySelectorAll("input[name=_categories]");
    springMVCTrash.forEach(input => input.remove());

    /**
     * Form Select
     */
    class FormSelect {
        constructor($el) {
            this.$el = $el;
            this.options = [...$el.children];
            this.init();
        }

        init() {
            this.createElements();
            this.addEvents();
            this.$el.parentElement.removeChild(this.$el);
        }

        createElements() {
            // Input for value
            this.valueInput = document.createElement("input");
            this.valueInput.type = "text";
            this.valueInput.name = this.$el.name;

            // Dropdown container
            this.dropdown = document.createElement("div");
            this.dropdown.classList.add("dropdown");

            // List container
            this.ul = document.createElement("ul");

            // All list options
            this.options.forEach((el, i) => {
                const li = document.createElement("li");
                li.dataset.value = el.value;
                li.innerText = el.innerText;

                if (i === 0) {
                    // First clickable option
                    this.current = document.createElement("div");
                    this.current.innerText = el.innerText;
                    this.dropdown.appendChild(this.current);
                    this.valueInput.value = el.value;
                    li.classList.add("selected");
                }

                this.ul.appendChild(li);
            });

            this.dropdown.appendChild(this.ul);
            this.dropdown.appendChild(this.valueInput);
            this.$el.parentElement.appendChild(this.dropdown);
        }

        addEvents() {
            this.dropdown.addEventListener("click", e => {
                const target = e.target;
                this.dropdown.classList.toggle("selecting");

                // Save new value only when clicked on li
                if (target.tagName === "LI") {
                    this.valueInput.value = target.dataset.value;
                    this.current.innerText = target.innerText;
                }
            });
        }
    }

    document.querySelectorAll(".form-group--dropdown select").forEach(el => {
        new FormSelect(el);
    });

    /**
     * Hide elements when clicked on document
     */
    document.addEventListener("click", function (e) {
        const target = e.target;
        const tagName = target.tagName;

        if (target.classList.contains("dropdown")) return false;

        if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
            return false;
        }

        if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
            return false;
        }

        document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
            el.classList.remove("selecting");
        });
    });

    /**
     * Switching between form steps
     */
    class FormSteps {
        bagsNo;
        categories;
        institution;
        pickUpDetails = {
            street: "",
            city: "",
            zipCode: "",
            date: "",
            time: "",
            comment: "Brak uwag"
        };

        constructor(form) {
            this.$form = form;
            this.$next = form.querySelectorAll(".next-step");
            this.$prev = form.querySelectorAll(".prev-step");
            this.$step = form.querySelector(".form--steps-counter span");
            this.currentStep = 1;

            this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
            const $stepForms = form.querySelectorAll("form > div");
            this.slides = [...this.$stepInstructions, ...$stepForms];

            this.init();
        }

        /**
         * Init all methods
         */
        init() {
            this.events();
            this.updateForm();
            this.validateSlide();
            this.summarize();
        }

        /**
         * All events that are happening in form
         */
        events() {
            // Next step
            this.$next.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    if (this.validateSlide(this.currentStep)) {
                        this.currentStep++;
                        this.updateForm();
                    }

                });
            });

            // Previous step
            this.$prev.forEach(btn => {
                btn.addEventListener("click", e => {
                    e.preventDefault();
                    this.currentStep--;
                    this.updateForm();
                });
            });

            // Form submit
            this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
        }

        /**
         * validation of each slide
         * @param currentStep
         * @returns {boolean}
         */

        validateSlide(currentStep) {
            switch (currentStep) {

                case 1: {
                    let checkedBoxes = $(this.slides[currentStep + 3]).find("input:checked");
                    if (checkedBoxes.length > 0) {
                        $(this.slides[currentStep + 3]).find(".form-error").text("");
                        this.categories = checkedBoxes.siblings("span.description").text().replace(/ /g, "").split(/\s/);
                        this.categories = this.categories.filter(string => {
                            return string !== "";
                        }).join(", ");
                        return true;
                    } else {
                        $(this.slides[currentStep + 3]).find(".form-error").text("Zaznacz przynajmniej jedną kategorię.");
                        return false;
                    }
                }
                    break;

                case 2: {
                    this.bagsNo = $(this.slides[currentStep + 3]).find("input").val();
                    if (this.bagsNo > 0) {
                        $(this.slides[currentStep + 3]).find(".form-error").text("");
                        return true;
                    } else {
                        $(this.slides[currentStep + 3]).find(".form-error").text("Przygotuj minimum jeden worek.");
                        return false;
                    }
                }
                    break;

                case 3: {
                    this.institution = $(this.slides[currentStep + 3])
                        .find("input:checked")
                        .siblings("span.description")
                        .find("div.title").text().trim();
                    if (this.institution !== "") {
                        $(this.slides[currentStep + 3]).find(".form-error").text("");
                        return true;
                    } else {
                        $(this.slides[currentStep + 3]).find(".form-error").text("Wybierz instytucję, którą chcesz obdarować.");
                        return false;
                    }
                }
                    break;

                case 4: {
                    let street = $(this.slides[currentStep + 3]).find("#street").val();
                    let city = $(this.slides[currentStep + 3]).find("#city").val();
                    let zipCode = $(this.slides[currentStep + 3]).find("#zipCode").val();
                    let date = $(this.slides[currentStep + 3]).find("#pickUpDate").val();
                    let time = $(this.slides[currentStep + 3]).find("#pickUpTime").val();
                    let comment = $(this.slides[currentStep + 3]).find("#pickUpComment").val();
                    if (street !== "" && city !== "" && zipCode !== "" && date !== "" && time !== "") {
                        this.pickUpDetails.street = street;
                        this.pickUpDetails.city = city;
                        this.pickUpDetails.zipCode = zipCode;
                        this.pickUpDetails.date = date;
                        this.pickUpDetails.time = time;
                        if (comment !== "") {
                            this.pickUpDetails.comment = comment;
                        }
                        return true;
                    } else {
                        $(this.slides[currentStep + 3]).find(".form-error").text("Podaj dane adresowe potrzebne do odbiory darowizny.");
                        return false;
                    }

                }
                    break;
            }
        }

        /**
         * get data from inputs and show them in summary
         * @param currentStep
         */
        summarize(currentStep) {
            let summaryTextWhat;

            if (this.bagsNo == 1) {
                summaryTextWhat = `${this.bagsNo} worek z kategorii: ${this.categories}`;
            } else {
                summaryTextWhat = `${this.bagsNo} worki z kategorii: ${this.categories}`;
            }
            let summaryTextToWhere = `Dla: ${this.institution}`;
            let summaryFirstSection = $(this.slides[currentStep + 3]).find("span.summary--text");
            let summarySecondSection = $(this.slides[currentStep + 3]).find("div.form-section--columns li");
            console.log("test");
            summaryFirstSection.eq(0).text(summaryTextWhat);
            summaryFirstSection.eq(1).text(summaryTextToWhere);

            summarySecondSection.eq(0).text(this.pickUpDetails.street);
            summarySecondSection.eq(1).text(this.pickUpDetails.city);
            summarySecondSection.eq(2).text(this.pickUpDetails.zipCode);
            summarySecondSection.eq(3).text(this.pickUpDetails.date);
            summarySecondSection.eq(4).text(this.pickUpDetails.time);
            summarySecondSection.eq(5).text(this.pickUpDetails.comment);
        }

        /**
         * Update form front-end
         * Show next or previous section etc.
         */
        updateForm() {
            this.$step.innerText = this.currentStep;

            // TODO: Validation refactor

            this.slides.forEach(slide => {
                slide.classList.remove("active");

                if (slide.dataset.step == this.currentStep) {
                    slide.classList.add("active");
                }
            });

            this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
            this.$step.parentElement.hidden = this.currentStep >= 5;

            if (this.currentStep == 5) {
                this.summarize(this.currentStep);
            }
        }

    }

    const form = document.querySelector(".form--steps");
    if (form !== null) {
        new FormSteps(form);
    }

});