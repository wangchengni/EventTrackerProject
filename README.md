# EventTrackerProject
This project is used to track a mountain's lift and routes condition.
A mountain has many routes and lifts and a lift has many routes.
Mountain table will have peak name and elevation level;
Lift table will have lift name, running time and carrier capacity;
Route table will have route name, distance, route level and snow condition;
At first I decide to have another snow_condition table which will have a mant-to-many
relationship with the routes. However, when I try to use the JasonIngore to this relationship,
it will throw an error. Well, when I am not using the JasonIgnore for this relationship,
it works ok, just looks ugly with many nested data.

## OverView
This weekend project, I finished the route table, which has the following search
methods:
1. getAllRoutes;
2. findRouteByRouteId;
3. findRouteByNameOrLevel;
4. findBySnowCondition;
5. findByDistanceGreaterThan;
6. findAllOrderByLevel;(not quit perfect this method,
  as not sure how to find all instead of just find by level and order by level)
7. createNewRoute;
8. updateRoute;
9. DeleteRoute;
### REST API
Go To http://35.162.4.6:8083/skiRoute/api/routes

### HTML/JavaScript Front End
Use the Atom, JavaScript and HTML to make the front end page, preventDefault is
important to be set at first.
The website can use the route Id to find the route data, use the lift Id to find
the total distance. User can also create a new route under specific lift Id, user
can also change route information based on the route Id, also if some route information
is wrongly created, use can also use the route id to delete that.

### Angular Front End

## REST API Reference
|Return Type   | HTTP Method | URI          | Request Body |
|--------------|-------------|--------------|--------------|
| List<Route> | GET        | /api/Routes |     |
| Route       | GET        | /api/Routes/{id} | |
| List<Route> | GET        | /api/Routes/LiftName{name} | Route JSON|
| List<Route> | GET        | /api/condition/{snowcondition} | Route JSON|
| List<Route> | GET        | /api/Routes/distance{distance} | Route JSON|
| List<Route> | GET        | /api/Routes/orderBy{level} | Route JSON|
| Route       | POST       | /api/Lifts/{id}routes | Route JSON|
| Route       | PUT        | /api/Routes/{id} | Route JSON|
| Void        | DELETE     | /api/Lifts/{liftId}routes/{routeId} | Route JSON|



## Technologies Used
RESTFUL APIs, PostMan, Junit test, SQL WorkBench,

## Lessons Learned
Using RESTFUL is a great help and save much more than in creating all the methods,
it is also necessary to well design the database, otherwise, you may need change a lot
when creating relationship, entity, implements. At first, I have a table named condition in my
database, while, whatever any changes, or test, this table is not exist in the database, i can see
it from the schema, but can not retrieve it from the terminal or using the eclipse, it will always failed
in the test presenting sql #1064 error. Then, the easiest way for me is to delete the condition table and
created a new one named snow_condition, it works, though this table is not used right now, but fix it have a
lot of fun and need patient.
