<%-- 
Document   : results
Created on : Dec 7, 2015, 6:26:44 PM
Author     : andrew
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
        <link href="css/owfont-regular.css" rel="stylesheet" type="text/css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <title>TripCast Weather Route App</title>
        
        <link rel="stylesheet" type="text/css" href="css/main.css">
    </head>
    <body>
        <div class ="container">
        <div class="jumbotron text-center">
                <div class="page-header">
                    <h1>TRIPCAST</h1>
                </div>
                <p>Your solution to Mother Nature</p>
            </div>

            <form action="WeatherRouteHandler" method="POST">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="panel panel-primary">
                            <div class="panel-heading clearfix">
                                <i class="icon-calendar"></i>
                                <h2 class="panel-title text-center">Plan your trip!</h2>
                            </div>

                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-lg-4 col-sm-4">
                                        <div class="input-group">
                                            <span class="input-group-addon">Origin</span>
                                            <input type="text" class="form-control" placeholder="Enter a City Name" name="origin">
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-sm-4">
                                        <div class="input-group">
                                            <span class="input-group-addon">Destination</span>
                                            <input type="text" class="form-control" placeholder="Enter a City Name" name="destination"> 
                                        </div>
                                    </div>
                                    <div class="col-lg-4 col-sm-4">
                                        <div class="input-group" style="width:100%">
                                            <button type="submit" class="btn btn-primary btn-block">Get Weather</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>

            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12">               
                    <div class="panel panel-primary">
                        <div class="panel-heading clearfix">
                            <i class="icon-calendar"></i>
                            <h2 class="panel-title text-center">Results</h2>
                        </div>

                        <div class="panel-body">
                            <c:forEach var="tile" items="${tiles}">
                                <div class="col-lg-4 col-md-4 col-sm-6">
                                    <div class="tile">

                                        <h4 class="location">    ${tile.getLocationName().replace("\"", "")} </h4>
                                        <h4 class="temp">${tile.getTemperature()}&\#8457</h4>
                                        <p class="weatherIcon"><img src="http://openweathermap.org/img/w/${tile.getIcon()}.png" width="100px" height = "100px" ></img><br>
                                            <!--i class ="owf owf-${tile.getIconID()} owf-5x"></i--></p>
                                        <p>${tile.getWeatherDescription().replace("\"", "")}</p>
                                        <p>
                                            Humidity: ${tile.getHumidity()}% | Wind Speed: ${tile.getWindSpeed()}mph
                                        </p>
                                    </div>    
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

