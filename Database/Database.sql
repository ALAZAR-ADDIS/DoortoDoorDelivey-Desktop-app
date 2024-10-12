create database DoortoDoorDelivery

select *from Customers
drop table Customers

use DoortoDoorDelivery
create table Customers(
	FirstName varchar(20),
	LastName varchar(20),
	CUserName varchar(20),
	Role varchar(20),
	Password varchar(20),
	Email varchar(50),
	PhoneNumber varchar(20),
	Location varchar(30),
	AccountBalance decimal(20,3),
	Image varbinary(Max),
	Gender varchar(20),
	primary key( cUserName)

);



create table DeliveryMans(
	FirstName varchar(20),
	LastName varchar(20),
	DMUserName varchar(20),
	Role varchar(20),
	Password varchar(20),
	Email varchar(50),
	PhoneNumber varchar(20),
	Location varchar(30),
	AccountBalance decimal(20,3),
	Image Varbinary(Max),
	Gender varchar(20),	
	vehicleType varchar(20),
	IdImage varbinary(Max),
	Primary key( DMUsername)
);



create table SuperMarkets(
	FirstName varchar(20),
	SUserName varchar(20),
	Role varchar(20),
	Password varchar(20),
	Email varchar(50),
	PhoneNumber varchar(20),
	Location varchar(30),
	AccountBalance decimal(20,3),
    image varbinary(Max),
	Primary key( SUserName)
);



create table Product(
SUserName varchar(20),
ProductName varchar(20),
ProductDescription varchar(100),
ProductId varchar(20),
NumberProductAvailable  int,
ProductSold int,
price decimal (20,3),
image varbinary(Max),
primary key(ProductId),
foreign key(SUserName) references SuperMarkets(SUserName)

);







create table Orders(
CUserName varchar(20),
ProductId varchar(20),
pickUplocation varchar(30),
TimeofPurchase varchar(50),
OrderStatus varchar(30),
Amount int,
invoice VarBinary(Max),
primary key(ProductId,CuserName,TimeofPurchase),
foreign key(CUserName) references Customers(CUserName),
foreign key(ProductId) references product(ProductId)
);

drop table Orders
drop table OrderPickedBy




Create table OrderPickedBy(
ProductId varchar(20),
CUserName Varchar(20),
TimeOfPurchase varchar(50),
DMuserName  Varchar(20),

foreign key(ProductId,CUserName,TimeOfPurchase) references Orders(ProductId,CuserName,TimeofPurchase),
foreign key(DMuserName) references DeliveryMans(DMuserName)

);


create table cart(
CUserName varchar(20),
Name varchar(20),
Amount Int,
price Decimal(20,3),
productId varchar(20),
foreign key(CUserName) references Customers(CUserName)
)

create table ServiceProvider(
payableBallance Decimal(20,3),
AvailableBalance Decimal(20,3),

);

 


CREATE VIEW AcceptedOrdersInfo AS
SELECT 
    Customers.FirstName  as CustomerFirstName,
	Customers.LastName,
    Customers.PhoneNumber  as customerPhonenumber,
    SuperMarkets.FirstName  as SuperMarketFullName,
    SuperMarkets.Location,
    SuperMarkets.PhoneNumber as SuperMarkephonenumber,
    Orders.ProductID,
    Orders.Amount,
    Orders.Invoice
FROM 
    Orders
JOIN 
    Customers ON Orders.CUserName = Customers.CUserName
JOIN 
    Product ON Orders.ProductID = Product.ProductID
JOIN 
    SuperMarkets ON Product.SUserName = SuperMarkets.SUserName;





Create view IncomingOrders as

select orders.CuserName,Orders.Productid,Orders.pickUplocation,orders.TimeofPurchase,orders.OrderStatus,orders.amount,orders.Invoice,Product.SUserName from Orders
join Product on Product.ProductId=Orders.ProductId































