window.addEventListener("load", function(e) {
  console.log("document loaded");
  init();
  //e.preventDefault();
});

function init() {
  console.log("in init()");
  //TODO -setup event listeners for forms, etc.
  document.routeForm.lookup.addEventListener('click', function(event) {
    event.preventDefault();
    var routeId = document.routeForm.routeId.value;
    if (!isNaN(routeId) && routeId > 0) {
      getRoute(routeId);
    }
  });
  document.newRouteForm.addRoute.addEventListener('click', function(evt) {
    evt.preventDefault();
    let form = document.newRouteForm;
    let newRoute = {
      liftId: form.liftId.value,
      name: form.name.value,
      distance: form.distance.value,
      level: form.level.value,
      snowCondition: form.snowCondition.value
    }
    postRoute(newRoute);
  });
	document.updateRouteForm.updateRoute.addEventListener('click', function(evte) {
		evte.preventDefault();
		let form = document.updateRouteForm;
		let exRoute = {
			routeId: form.routeId.value,
			name: form.name.value,
			distance: form.distance.value,
			level: form.level.value,
			snowCondition: form.snowCondition.value
		}
		updateRoute(exRoute);
	});
	document.deleteRouteForm.deleteButton.addEventListener('click', function(event2) {
		event2.preventDefault();
		var routeId = document.deleteRouteForm.routeId.value;
		if (!isNaN(routeId) && routeId > 0) {
			deleteRoute(routeId);
		}
	})
}

///Get Route by Route id;
function getRoute(routeId) {
  let xhr = new XMLHttpRequest();
  xhr.open('GET', 'api/routes/' + routeId);
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        console.log('Requested succeded');
        let route = JSON.parse(xhr.responseText);
        displayRoute(route);
      } else if (xhr.readyState === 404) {
        displayError('Route' + routeId + 'not found');
      } else {
        displayError('Error restrieving film: ' + xhr.status)
      }
    }
  }
  xhr.send();
}

function displayError(msg) {
  var routeDiv = document.getElementById('routeData')
  routeDiv.textContent = msg;
	routeDiv.style.color ="Red"
}

function displayRoute(route) {
  var dataDiv = document.getElementById('routeData');
  dataDiv.textContent = '';
  let h1 = document.createElement('h1');
  h1.textContent = route.name;
  dataDiv.appendChild(h1);

  let ul = document.createElement('ul');
  dataDiv.appendChild(ul);
  let li = document.createElement('li');
  if (route.distance > 1) {
    li.textContent = 'Route Distance :' + route.distance + ' miles';
    dataDiv.appendChild(li);
  } else {
    li.textContent = 'Route Distance :' + route.distance + ' mile';
    dataDiv.appendChild(li);
  };

  li = document.createElement('li');
  li.textContent = 'Route Level :' + route.level;
  dataDiv.appendChild(li);
  li = document.createElement('li');
  li.textContent = `Today's Route Condition: ` + route.snowCondition;
  dataDiv.appendChild(li);
	li = document.createElement('li');
  li.textContent = `Lift Name: ` + route.lift.name;
  dataDiv.appendChild(li);
}
///Create New Route
function postRoute(newRoute) {
  console.log(newRoute);
  let xhr = new XMLHttpRequest();
  xhr.open('POST', `api/lifts/${newRoute.liftId}/routes`)
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4) {
      //question why null name and negative distance passed the teset here but failed on Postman

        if (xhr.status === 201 || xhr.status === 200) {
          console.log('Request succeded');
          let route = JSON.parse(xhr.responseText);
          console.log(xhr.getResponseHeader('Location'));
          console.log(route);
          displayRoute(route);
        }
       else {
        console.log('Route created failed with status :' + xhr.status);
        console.log(xhr.status + ':' + xhr.responseText);
      }
    }
  }
  xhr.setRequestHeader("Content-type", "application/json");
  var userObjectJson = JSON.stringify(newRoute);
  xhr.send(userObjectJson);
}
////update route here
function updateRoute(exRoute){
	console.log(exRoute);
	let xhr = new XMLHttpRequest();
	xhr.open('PUT', 'api/routes/' + exRoute.routeId);
	xhr.onreadystatechange = function(){
		if(xhr.readyState === 4){
			if(xhr.status ===200){
				console.log('Request succeded');
				let route = JSON.parse(xhr.responseText);
				console.log(xhr.getResponseHeader('Location'));
				console.log(route);
				displayRoute(route);
			}
			else{
				console.log('Route update failed with status :'+
			  xhr.status);
				console.log(xhr.status +':'+xhr.responseText);
			}
		}
	}
	xhr.setRequestHeader("Content-type", "application/json");
  var userObjectJson = JSON.stringify(exRoute);
  xhr.send(userObjectJson);
}
/////delete exitsing routes
function deleteRoute(routeId){
	let xhr = new XMLHttpRequest
	let route = getRoute(routeId);
	xhr.open('DELETE','api/routes/' + routeId);
	xhr.onreadystatechange = function(){
		if(xhr.readyState ===4){
			if(xhr.status===204 || xhr.status ===200){
				console.log('Delete succeded');
			}
			else if(xhr.status ===404){
				console.log('Route for'+routeId+'not found');
			}
			else{
				console.log('Error restrieving route: '+xhr.status);
			}
		}
	}
	xhr.send();
}
