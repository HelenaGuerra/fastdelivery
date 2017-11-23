var geocoder;
var map;
var marker;
var testeDouglas;
var teste;

function teste() {

	alert("testando");

}

function initialize() {

	console.log("blur  91");

	var latlng = new google.maps.LatLng(-23.4905034, -46.618766699);

	var options = {
		zoom : 15,
		center : latlng,
		styles : style,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};

	console.log("initialize  17");

	map = new google.maps.Map(document.getElementById("mapa"), options);

	geocoder = new google.maps.Geocoder();

	marker = new google.maps.Marker({
		map : map,
		draggable : false,
		animation : google.maps.Animation.BOUNCE
	});

}

$(document)
		.ready(
				function() {

					initialize();

					console.log("teste");

					function carregarNoMapa(endereco) {
						geocoder
								.geocode(
										{
											'address' : endereco + ', Brasil',
											'region' : 'BR'
										},
										function(results, status) {

											testeDouglas = results;

											teste = testeDouglas;

											$('#lat')
													.val(
															testeDouglas[0].geometry.location
																	.lat());

											$('#lon')
													.val(
															testeDouglas[0].geometry.location
																	.lng());

											if (status == google.maps.GeocoderStatus.OK) {
												if (results[0]) {
													var latitude = results[0].geometry.location
															.lat();
													var longitude = results[0].geometry.location
															.lng();

													$('#txtEndereco')
															.val(
																	results[0].formatted_address);
													$('#txtLatitude').val(
															latitude);
													$('#txtLongitude').val(
															longitude);

													var location = new google.maps.LatLng(
															latitude, longitude);
													marker
															.setPosition(location);
													map.setCenter(location);
													map.setZoom(16);
												}
											}
										})
					}

					$("#btnEndereco").click(function() {
						if ($(this).val() != "")
							carregarNoMapa($("#txtEndereco").val());
					})

					$("#txtEndereco").blur(function() {

						console.log("teste");

						if ($(this).val() != "")
							carregarNoMapa($(this).val());

					})

					google.maps.event.addListener(marker, 'drag', function() {
						console.log("google.maps.event.addListener  95");
						geocoder.geocode({
							'latLng' : marker.getPosition()
						}, function(results, status) {
							if (status == google.maps.GeocoderStatus.OK) {
								if (results[0]) {
									$('#txtEndereco').val(
											results[0].formatted_address);
									$('#txtLatitude').val(
											marker.getPosition().lat());
									$('#txtLongitude').val(
											marker.getPosition().lng());
								}
							}
						});
					});

					$("#txtEndereco")
							.autocomplete(
									{
										source : function(request, response) {

											console.log("autoComplete 117");

											geocoder
													.geocode(
															{
																'address' : request.term
																		+ ', Brasil',
																'region' : 'BR'
															},
															function(results,
																	status) {
																response($
																		.map(
																				results,
																				function(
																						item) {
																					return {
																						label : item.formatted_address,
																						value : item.formatted_address,
																						latitude : item.geometry.location
																								.lat(),
																						longitude : item.geometry.location
																								.lng()
																					}
																				}));
															})
										},
										select : function(event, ui) {
											$("#txtLatitude").val(
													ui.item.latitude);
											$("#txtLongitude").val(
													ui.item.longitude);
											var location = new google.maps.LatLng(
													ui.item.latitude,
													ui.item.longitude);
											marker.setPosition(location);
											map.setCenter(location);
											map.setZoom(16);
										}
									});

					$("form").submit(

							function(event) {
								event.preventDefault();

								console.log("submit  163");

								var endereco = $("#txtEndereco").val();
								var latitude = $("#txtLatitude").val();
								var longitude = $("#txtLongitude").val();

								alert("Endereço: " + endereco + "\nLatitude: "
										+ latitude + "\nLongitude: "
										+ longitude);
							});

				});