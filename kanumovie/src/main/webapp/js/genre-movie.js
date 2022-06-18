const BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=e51d70c65b46eb8bd60cafccc9325e8b&language=ko-KO&with_genres="
let genreId = parseInt(document.querySelector('input').getAttribute('value'));
let genreURL = genreId + "&page=";
let sessionId = document.querySelector('#sessionId').value;

let count;
let list = document.querySelectorAll('div.movie-card-container');
list.forEach(container => {
	console.log(window.sessionStorage);
	let number = container.getAttribute('data-value');
	movieList(number);
	count = parseInt(number) + 1;
})

function movieList(num) {
	let API_URL = BASE_URL + genreURL + num;
	if (num > 2) {
		let div = document.createElement('div');
		div.setAttribute('class', 'movie-card-container');
		div.setAttribute('data-value', num);
		document.querySelector('div.infinite').append(div);

	}
	getData(API_URL, num);
}

function getData(url, num) {
	fetch(url)
		.then(response => response.json())
		.then(data => data.results)
		.then(arr => {
			let pageCnt = num;
			arr.forEach((elem) => {
				let card = makeCard(elem);
				let container = document.querySelectorAll('div.movie-card-container');
				container.forEach((con) => {
					if (con.getAttribute('data-value') == pageCnt) {
						con.append(card);
					}
				})
			})
			rating();
			getLikes();
		})
}

function makeCard(obj) {
	let card = document.createElement('div');
	card.setAttribute('class', 'movie-card');
	card.setAttribute('onclick', "location.href='movieInfo.do?id=" + obj.id + "'");
	let img = document.createElement('img');
	img.setAttribute('src', 'https://image.tmdb.org/t/p/w500' + obj.poster_path);
	card.append(img);
	let info = document.createElement('div');
	let title = document.createElement('p');
	title.innerHTML = obj.title;
	info.append(title);
	let rate = document.createElement('div');
	rate.setAttribute('class', 'star-rate');
	rate.setAttribute('data-rate', obj.vote_average);
	for (let i = 0; i < 5; i++) {
		let star = document.createElement('span');
		star.setAttribute('class', 'fa fa-star');
		rate.append(star);
	}
	let button = makeButton(obj);
	let input = document.createElement('input');
	input.setAttribute('type', 'hidden');
	input.setAttribute('value', obj.id);
	card.append(input);
	card.append(button);
	info.append(rate);
	card.append(info);
	return card;
}

function makeButton(obj) {
	let div = document.createElement('div');
	let button = document.createElement('button');
	button.setAttribute('type', 'button');
	button.setAttribute('class', 'w3-button w3-black w3-round');
	button.addEventListener('click', function(e) {
		console.log("heart");
		e.stopPropagation();
		let heart = this.firstChild;
		let cmd = "";
		if (!!sessionId) {
			if (heart.style.color == 'red') {
				heart.setAttribute('style', 'color:grey');
				cmd = "delete";
			} else if (heart.style.color == 'grey') {
				heart.setAttribute('style', 'color:red');
				cmd = "insert";
			}
		} else if (!sessionId) {
			alert("로그인이 필요한 기능입니다.");	
		}
		let param = 'cmd=' + cmd + '&id=' + this.children[0].getAttribute('id').substring(6) + '&email=' + sessionId;
		if (cmd == 'insert') {
			fetch('likes.do', {
				method:'POST', 
				headers:{'Content-Type':'application/x-www-form-urlencoded'},
				body: param
			})
				.then(response => response.json())
				.then(message => console.log(message))
				.catch(err => console.log(err))
		} else if (cmd == 'delete') {
			fetch('likesDelete.do', {
				method:'POST',
				headers:{'Content-Type':'application/x-www-form-urlencoded'},
				body: param
			})
				.then(response => response.json())
				.then(message => console.log(message))
				.catch(err => console.log(err))
		}
	})
	button.setAttribute('style', 'background:none;z-index:999;')
	let icon = document.createElement('i');
	icon.setAttribute('class', 'fa fa-heart');
	icon.setAttribute('style', 'color:grey');
	icon.setAttribute('id', 'movie-' + obj.id);
	button.append(icon);
	div.append(button);
	return div;
}


let param = {email: sessionId };
function getLikes() {
	if (sessionId) {
		fetch('userLikesSelectList.do', {
			method: 'POST',
			headers: { 'Content-Type': 'application/json' },
			body: JSON.stringify(param)
		})
			.then(response => response.json())
			.then(data => {
				let movieData = document.querySelectorAll('.movie-card input');
				movieData.forEach((elem) => {
					data.forEach((like) => {
						if (like.id == elem.getAttribute('value')) {
							document.querySelector('#movie-' + like.id).setAttribute('style', 'color:red');
							console.log(document.querySelector('#movie-' + like.id).parentElement);
						} else {
							console.log(false);
						}
					})
				})
			});
	}
}






