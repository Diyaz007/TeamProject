var location_swiper = new Swiper(".stat-items", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    breakpoints: {
        768: {
            slidesPerView: 2,
        },

        1100: {
            slidesPerView: 4,
        },
    },
});

var review_swiper = new Swiper(".review-container", {
    slidesPerView:1,
    spaceBetween: 30,
    loop: true,
    pagination: {
        el: ".review-swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".next",
        prevEl: ".prev",
    },
    breakpoints: {
        1250: {
            slidesPerView: 2,
        },
        1249: {
            slidesPerView:1,
        },
    },
});

var tips_swiper = new Swiper(".cards", {
    slidesPerView:1,
    spaceBetween: 30,
    loop: true,
    pagination: {
        el: ".tips-swiper-pagination",
        clickable: true,
    },
    navigation: {
        nextEl: ".tips-next",
        prevEl: ".tips-prev",
    },
    breakpoints: {
        1250: {
            slidesPerView: 2,
        },
        1249: {
            slidesPerView:1,
        },
    },
});

var service_swiper = new Swiper(".service-types", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    navigation: {
        nextEl: ".service-next",
        prevEl: ".service-prev",
    },
    breakpoints: {
        1250: {
            slidesPerView: 4,
        },
        960: {
            slidesPerView: 2,
        },
    },
});

var locations_swiper = new Swiper(".locations", {
    slidesPerView: 1,
    spaceBetween: 30,
    loop: true,
    navigation: {
        nextEl: ".location-next",
        prevEl: ".location-prev",
    },
    breakpoints: {
        1250: {
            slidesPerView: 4,
        },
        960: {
            slidesPerView: 2,
        },
    },
});


document.addEventListener("DOMContentLoaded", () => {
    const hamMenu = document.querySelector(".ham-menu");
    const offScreenMenu = document.querySelector(".off-screen-menu");

    hamMenu.addEventListener("click", () => {
        hamMenu.classList.toggle("active");
        offScreenMenu.classList.toggle("active");
    });
})

document.addEventListener('DOMContentLoaded', function() {
    let showMoreButton = document.querySelector('.show-more');
    let hasMore = document.querySelector('.has-more');


    if (hasMore.textContent !== 'true') {
        showMoreButton.style.display = 'none';
    }

    showMoreButton.addEventListener('click', function () {
        let url = new URL(window.location.href);
        let size = parseInt(url.searchParams.get('size') || '6');
        size += 3
        url.searchParams.set('size', size);
        window.location.href = url.toString();
    });
});

