<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache"> 
    <meta http-equiv="Cache-Control" content="no-cache"> 
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">
    
    <meta name="robots" content="noindex, nofollow">
    <meta name="googlebot" content="noindex, nofollow">
    <title>MedyaSoft Simulate</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">   
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    
    <style type="text/css">
	.row-padding {
	    margin-top: 25px;
	    margin-bottom: 25px;
	}
    </style>
    
    
    
    
</head>
<body>

	<div role="navigation">
		<div class="navbar navbar-inverse">
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">		
					<li><a href="generate-data">Generate Data</a></li>
					<li><a href="simulate-data">Simulate Data</a></li>
					<li><a href="search-form">Search Station</a></li>
					<li><a href="shared-form">Shared Station</a></li>
					<li><a href="time-form">Time Search</a></li>
				</ul>
			</div>
		</div>
	</div>
	

	<c:choose>
		<c:when test="${mode == 'MODE_HOME'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Welcome to Simulate</h1>
				</div>
			</div>
		</c:when>
			<c:when test="${mode == 'MODE_GENERATE_DATA'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Data is generated !</h1>
				</div>
			</div>
		</c:when>
	
	<c:when test="${mode == 'MODE_SIMULATE_DATA'}">
			<div class="container" id="homeDiv">
				<div class="jumbotron text-center">
					<h1>Data is simulated !</h1>
				</div>
			</div>
		</c:when>
	<c:when test="${mode == 'MODE_SEARCH_FORM'}">
		<div class="container text-center">
		<h2><b>Stations</b></h2>
				<form class="form-horizontal"  method="POST" action="search-results">
					<div class="container">
						<div class="row">
					        <div class="col-sm-6 col-sm-offset-3">
					            <div id="imaginary_container"> 
					                	<h4>
										  Vehicle Codes:
										  <small class="text-muted">'Oto 1' 'Oto 2' 'Oto 3' 'Oto 4' 'Oto 5' 'Oto 6'</small>
										  <small class="text-muted">'Mini 1' 'Mini 2' 'Mini 3' 'Mini 4' 'Mini 5' 'Mini 6'</small>
										</h4>
										<div class="input-group stylish-input-group">
					                    <input required type="text" class="form-control"  placeholder="Vehicle Code" name="rootName" value="${routeName}" >
					                    <span class="input-group-addon">
					                        <button type="submit">
					                            <span class="glyphicon glyphicon-search"></span>
					                        </button>  
					                    </span>
					                </div>
					            </div>
					        </div>
						</div>
					</div>
				</form>
			</div>
</c:when>
<c:when test="${mode == 'MODE_SEARCH_RESULTS'}">
			<hr>
			<div class="container text-center" id="tasksDiv">
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								
								<th><h4><b>Route</b></h4></th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="routeStation" items="${routeStations}">
							
								<tr>
									<td>${routeStation.station.stationName }</td>
								
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		
	</c:when>	
	<c:when test="${mode == 'MODE_SHARED_FORM'}">
		<form class="form-horizontal" method="POST" action="shared-results">
		
					<h4 align="center">
						Vehicle Codes:
						<small class="text-muted">'Oto 1' 'Oto 2' 'Oto 3' 'Oto 4' 'Oto 5' 'Oto 6'</small>
						<small class="text-muted">'Mini 1' 'Mini 2' 'Mini 3' 'Mini 4' 'Mini 5' 'Mini 6'</small>
					</h4>
					<div class="form-group">
						<label class="control-label col-md-3">1st Vehicle</label>
						<div class="col-md-7">
							<input required type="text" class="form-control" name="left" value="${left}"/>
						</div>				
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">2nd Vehicle</label>
						<div class="col-md-7">
							<input required type="text" class="form-control" name="right" value="${right}"/>
						</div>				
					</div>
					<div class="form-group" style=" margin: 0;background-color: transparent;text-align: center;">
						<input required  type="submit" class="btn btn-primary" value="Search"/>
					</div>
				</form>
	</c:when>
	<c:when test="${mode == 'MODE_SHARED_RESULTS'}">
			<hr>
			<div class="container text-center" id="tasksDiv">
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								
								<th><h4><b>Stations</b></h4></th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="station" items="${sharedStations}">
							
								<tr>
									<td>${station.stationName }</td>
								
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		
	</c:when>
	<c:when test="${mode == 'MODE_TIME_FORM'}">
		<form class="form-horizontal" method="POST" action="time-results">
					<h4 align="center">
						Search according to Time
						<small class="text-muted">as Vehicle and Station</small>
						
					</h4>
					<div class="form-group">
						<label class="control-label col-md-3">Time</label>
						<div class="col-md-7">
							<input required type="time" class="form-control" name="time" value="${time}"/>
						</div>				
					</div>
					<div class="form-group" style=" margin: 0;background-color: transparent;text-align: center;">
						<input  type="submit" class="btn btn-primary" value="Search"/>
					</div>
				</form>
	</c:when>
	<c:when test="${mode == 'MODE_TIME_RESULTS'}">
			<hr>
			<div class="container text-center" id="tasksDiv">
				<div class="table-responsive">
					<table class="table table-striped table-bordered text-left">
						<thead>
							<tr>
								
								<th><h4><b>Vehicle Code</b></h4></th>
								<th><h4><b>Station Name</b></h4></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="voyage" items="${voyages}">
							
								<tr>
									<td>${voyage.route.routeName }</td>
								
									<td>${voyage.findStationInTime(date).stationName }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
	</c:when>	
	</c:choose>
	
</body>
</html>