-- create tables from .dat files
drop table if exists Items;
drop table if exists Users;
drop table if exists Bids;
drop table if exists Bidders;
drop table if exists Categories;
drop table if exists Sellers;
drop table if exists Users;
-- create table Users
create table Users (
  UserID varchar(100) primary key,
  Country varchar(100),
  Location varchar(100),
  Rating int
);
-- create table Bidders
create table Bidders (
  UserID varchar(100),
  foreign key (UserID) references Users(UserID)
);
-- create table Sellers
create table Sellers (
  UserID varchar(100),
  foreign key (UserID) references Users(UserID)
);
-- create table Bids
create table Bids (
  UserID varchar(100),
  Time timestamp,
  Amount decimal(10,2),
  ItemID int,
  foreign key (ItemID) references Items(ItemID),
  foreign key (UserID) references Bidders(UserID)
);
create table Items (
  ItemID int primary key,
  Name varchar(100),
  Currently decimal(10,2),
  First_Bid decimal(10,2),
  Number_of_Bids int,
  Started timestamp,
  Ends timestamp,
  SellerID varchar(100),
  Description text,
  Buy_Price decimal(10,2),
  foreign key (SellerID) references Sellers(UserID)
);
-- create table Categories
create table Categories (
  ItemID int,
  Category varchar(100),
  foreign key (ItemID) references Items(ItemID)
);

