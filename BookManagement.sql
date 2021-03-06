USE [master]
GO
/****** Object:  Database [BookManagementDB]    Script Date: 8/30/2020 7:29:39 PM ******/
CREATE DATABASE [BookManagementDB]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'BookManagementDB', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\BookManagementDB.mdf' , SIZE = 4096KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'BookManagementDB_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\BookManagementDB_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [BookManagementDB] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [BookManagementDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [BookManagementDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [BookManagementDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [BookManagementDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [BookManagementDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [BookManagementDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [BookManagementDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [BookManagementDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [BookManagementDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [BookManagementDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [BookManagementDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [BookManagementDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [BookManagementDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [BookManagementDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [BookManagementDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [BookManagementDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [BookManagementDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [BookManagementDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [BookManagementDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [BookManagementDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [BookManagementDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [BookManagementDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [BookManagementDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [BookManagementDB] SET RECOVERY FULL 
GO
ALTER DATABASE [BookManagementDB] SET  MULTI_USER 
GO
ALTER DATABASE [BookManagementDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [BookManagementDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [BookManagementDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [BookManagementDB] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [BookManagementDB] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'BookManagementDB', N'ON'
GO
USE [BookManagementDB]
GO
/****** Object:  Table [dbo].[tblBooks]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblBooks](
	[ID] [varchar](50) NOT NULL,
	[Title] [varchar](100) NULL,
	[Author] [varchar](50) NULL,
	[Price] [float] NULL,
	[Category] [varchar](50) NULL,
	[ImportDate] [datetime] NULL,
	[Quantity] [int] NULL,
	[Image] [varchar](50) NULL,
	[Description] [varchar](max) NULL,
	[Status] [varchar](10) NULL,
 CONSTRAINT [PK_tblBooks] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblDiscountCodeHistory]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblDiscountCodeHistory](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[DiscountCode] [varchar](25) NULL,
	[OrderID] [int] NULL,
	[UserID] [varchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblDiscountCodes]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblDiscountCodes](
	[ID] [varchar](25) NOT NULL,
	[DiscountPercent] [int] NULL,
	[CreateDate] [datetime] NULL,
 CONSTRAINT [PK__tblDisco__3214EC27D6444651] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrderDetails]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrderDetails](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[OrderID] [int] NULL,
	[BookID] [varchar](50) NULL,
	[Price] [float] NULL,
	[Quantity] [int] NULL,
	[Total] [float] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblOrders]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblOrders](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[CustomerID] [varchar](50) NULL,
	[Total] [float] NULL,
	[OrderDate] [datetime] NULL,
	[PaymentStatus] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblRoles]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblRoles](
	[RoleID] [int] NOT NULL,
	[RoleName] [varchar](10) NULL,
 CONSTRAINT [PK_tblRoles] PRIMARY KEY CLUSTERED 
(
	[RoleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[tblUsers]    Script Date: 8/30/2020 7:29:39 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[tblUsers](
	[Username] [varchar](50) NOT NULL,
	[Password] [varchar](50) NULL,
	[Fullname] [varchar](50) NULL,
	[Role] [int] NULL,
 CONSTRAINT [PK_tblUsers] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'1', N'Java Programming', N'Keshav Patel', 49.99, N'Programming Language', CAST(N'2020-08-25 00:00:00.000' AS DateTime), 7, N'img/javaProgramming.jpg', N'The Beginning Beginner''s Guide (Beginning Beginners Guide Book 1)', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'2', N'C# For Beginners', N'Jonas Fagerberg', 109.99, N'Programming Language', CAST(N'2020-08-25 00:00:00.000' AS DateTime), 5, N'img\2.jpg', N'The tactical guidebook - Learn CSharp by coding', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'3', N'Introduction to Information Technology', N'Rajaraman', 79.99, N'IT', CAST(N'2020-08-25 00:00:00.000' AS DateTime), 8, N'img/I2IT.jpg', N'Introduction to Information Technology', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'4', N'ASP.NET MVC 5', N'Jonas Fagerberg', 36.99, N'IT', CAST(N'2020-08-25 00:00:00.000' AS DateTime), 8, N'img/AspMvc5.jpg', N'Building a Website with Visual Studio 2015 and C Sharp: The Tactical Guidebook', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'5', N'A Smarter Way to Learn JavaScript', N'Mark Myers', 9.99, N'IT', CAST(N'2020-08-25 00:00:00.000' AS DateTime), 9, N'img/js.jpg', N'A Smarter Way to Learn JavaScript: The new approach that uses technology to cut your effort in half', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'6', N'Clean Code: A Handbook of Agile Software Craftsmanship', N'Robert C. Martin', 28.79, N'Software Design', CAST(N'2020-08-26 22:33:08.640' AS DateTime), 9, N'img\6.jpg', N'Even bad code can function. But if code isn''t clean, it can bring a development organization to its knees. Every year, countless hours and significant resources are lost because of poorly written code. But it doesn''t have to be that way.', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'7', N'Clean Agile', N'Martin Robert C.', 32.31, N'Software Design', CAST(N'2020-08-27 11:57:48.223' AS DateTime), 3, N'img\7.jpg', N'Clean Agile: Back to Basics (Robert C. Martin Series)', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'8', N'Head First Python', N'Paul Barry', 39.99, N'Programming Language', CAST(N'2020-08-27 12:20:28.810' AS DateTime), 8, N'img\8.jpg', N'Head First Python: A Brain-Friendly Guide', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'9', N'The Ruby Programming Language', N'David Flanagan', 28.49, N'Programming Language', CAST(N'2020-08-27 12:24:17.640' AS DateTime), 3, N'img\9.jpg', N'The Ruby Programming Language: Everything You Need to Know', N'InActive')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'DDS', N'Data Structures and Algorithms Made Easy', N'Narasimha Karumanchi', 42.59, N'Others', CAST(N'2020-08-27 21:56:29.617' AS DateTime), 9, N'img\DDS.jpg', N'"Data Structures And Algorithms Made Easy: Data Structure And Algorithmic Puzzles" is a book that offers solutions to complex data structures and algorithms.', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'JSAJ01', N'JavaScript and JQuery', N'Jon Duckett', 12, N'Programming Language', CAST(N'2020-08-30 16:29:32.687' AS DateTime), 10, N'img\JSAJ01.jpg', N'JavaScript and JQuery: Interactive Front-End Web Development', N'Active')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'q', N'q', N'q', 1, N'Others', CAST(N'2020-08-27 13:57:11.073' AS DateTime), 9, N'img\q.jpg', N'q', N'InActive')
INSERT [dbo].[tblBooks] ([ID], [Title], [Author], [Price], [Category], [ImportDate], [Quantity], [Image], [Description], [Status]) VALUES (N'SCM01', N'Essential Scrum', N'Kenneth S. Rubin', 35, N'Software Design', CAST(N'2020-08-29 19:17:23.793' AS DateTime), 0, N'img\SCM01.jpg', N'Essential Scrum: A Practical Guide to the Most Popular Agile Process (Addison-Wesley Signature): A Practical Guide To The Most Popular Agile Process (Addison-Wesley Signature Series (Cohn))', N'Active')
SET IDENTITY_INSERT [dbo].[tblDiscountCodeHistory] ON 

INSERT [dbo].[tblDiscountCodeHistory] ([ID], [DiscountCode], [OrderID], [UserID]) VALUES (8, N'99', 14, N'b')
INSERT [dbo].[tblDiscountCodeHistory] ([ID], [DiscountCode], [OrderID], [UserID]) VALUES (9, N'98', 15, N'b')
INSERT [dbo].[tblDiscountCodeHistory] ([ID], [DiscountCode], [OrderID], [UserID]) VALUES (10, N'trumcuoi', 16, N'b')
SET IDENTITY_INSERT [dbo].[tblDiscountCodeHistory] OFF
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'98', 98, CAST(N'2020-08-30 18:57:12.020' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'99', 99, CAST(N'2020-08-30 18:46:24.297' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'A15', 15, CAST(N'2020-08-28 00:09:06.423' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'END8', 18, CAST(N'2020-08-30 16:36:35.170' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'HAPPY9', 9, CAST(N'2020-08-29 19:18:36.077' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'NEW20', 20, CAST(N'2020-08-27 00:09:06.423' AS DateTime))
INSERT [dbo].[tblDiscountCodes] ([ID], [DiscountPercent], [CreateDate]) VALUES (N'TRUMCUOI', 50, CAST(N'2020-08-28 00:01:06.423' AS DateTime))
SET IDENTITY_INSERT [dbo].[tblOrderDetails] ON 

INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (1, 2, N'1', 49.99, 5, 249.95)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (2, 2, N'3', 79.99, 9, 719.91)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (3, 3, N'1', 49.99, 1, 49.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (4, 3, N'2', 109.99, 1, 109.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (5, 3, N'3', 79.99, 1, 79.98)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (6, 3, N'4', 36.99, 1, 36.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (7, 4, N'8', 39.99, 1, 39.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (8, 4, N'9', 28.49, 1, 28.49)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (9, 5, N'1', 49.99, 9, 449.91)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (10, 6, N'9', 28.49, 5, 142.44)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (11, 7, N'7', 32.31, 6, 193.86)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (12, 8, N'1', 49.99, 3, 149.97)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (13, 8, N'SCM01', 35, 1, 35)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (14, 9, N'JSAJ01', 12, 10, 120)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (15, 9, N'SCM01', 35, 8, 280)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (16, 10, N'1', 49.99, 6, 499.9)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (17, 11, N'4', 36.99, 2, 73.98)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (18, 12, N'1', 49.99, 1, 49.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (19, 13, N'2', 109.99, 1, 109.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (20, 14, N'2', 109.99, 1, 109.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (21, 15, N'1', 49.99, 1, 49.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (22, 15, N'2', 109.99, 1, 109.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (23, 15, N'3', 79.99, 1, 79.98)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (24, 15, N'4', 36.99, 1, 36.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (25, 16, N'1', 49.99, 1, 49.99)
INSERT [dbo].[tblOrderDetails] ([ID], [OrderID], [BookID], [Price], [Quantity], [Total]) VALUES (26, 16, N'2', 109.99, 1, 109.99)
SET IDENTITY_INSERT [dbo].[tblOrderDetails] OFF
SET IDENTITY_INSERT [dbo].[tblOrders] ON 

INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (1, N'b', 969.86, CAST(N'2020-08-28 23:56:42.383' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (2, N'b', 969.86, CAST(N'2020-08-28 23:58:14.227' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (3, N'b', 276.95, CAST(N'2020-08-29 00:00:33.647' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (4, N'b', 68.48, CAST(N'2020-08-29 00:01:55.933' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (5, N'b', 449.91, CAST(N'2020-08-29 00:15:39.827' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (6, N'b', 142.44, CAST(N'2020-08-29 18:09:52.477' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (7, N'b', 193.86, CAST(N'2020-08-29 18:10:53.120' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (8, N'b', 184.97, CAST(N'2020-08-29 19:24:25.887' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (9, N'b', 999.99, CAST(N'2020-08-30 16:41:14.880' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (10, N'b', 299.94, CAST(N'2020-08-30 18:59:08.123' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (11, N'b', 73.98, CAST(N'2020-08-30 19:05:24.160' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (12, N'b', 49.99, CAST(N'2020-08-30 19:07:47.040' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (13, N'b', 109.99, CAST(N'2020-08-30 19:10:26.567' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (14, N'b', 109.99, CAST(N'2020-08-30 19:15:12.117' AS DateTime), N'Unpaid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (15, N'b', 1, CAST(N'2020-08-30 19:24:25.830' AS DateTime), N'Paid')
INSERT [dbo].[tblOrders] ([ID], [CustomerID], [Total], [OrderDate], [PaymentStatus]) VALUES (16, N'b', 79.98, CAST(N'2020-08-30 19:27:16.127' AS DateTime), N'Unpaid')
SET IDENTITY_INSERT [dbo].[tblOrders] OFF
INSERT [dbo].[tblRoles] ([RoleID], [RoleName]) VALUES (1, N'Admin')
INSERT [dbo].[tblRoles] ([RoleID], [RoleName]) VALUES (2, N'User')
INSERT [dbo].[tblUsers] ([Username], [Password], [Fullname], [Role]) VALUES (N'a', N'a', N'Ad', 1)
INSERT [dbo].[tblUsers] ([Username], [Password], [Fullname], [Role]) VALUES (N'b', N'b', N'Hieu', 2)
ALTER TABLE [dbo].[tblDiscountCodeHistory]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscountCodeHistory_tblDiscountCodes] FOREIGN KEY([DiscountCode])
REFERENCES [dbo].[tblDiscountCodes] ([ID])
GO
ALTER TABLE [dbo].[tblDiscountCodeHistory] CHECK CONSTRAINT [FK_tblDiscountCodeHistory_tblDiscountCodes]
GO
ALTER TABLE [dbo].[tblDiscountCodeHistory]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscountCodeHistory_tblOrders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[tblOrders] ([ID])
GO
ALTER TABLE [dbo].[tblDiscountCodeHistory] CHECK CONSTRAINT [FK_tblDiscountCodeHistory_tblOrders]
GO
ALTER TABLE [dbo].[tblDiscountCodeHistory]  WITH CHECK ADD  CONSTRAINT [FK_tblDiscountCodeHistory_tblUsers] FOREIGN KEY([UserID])
REFERENCES [dbo].[tblUsers] ([Username])
GO
ALTER TABLE [dbo].[tblDiscountCodeHistory] CHECK CONSTRAINT [FK_tblDiscountCodeHistory_tblUsers]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblBooks] FOREIGN KEY([BookID])
REFERENCES [dbo].[tblBooks] ([ID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblBooks]
GO
ALTER TABLE [dbo].[tblOrderDetails]  WITH CHECK ADD  CONSTRAINT [FK_tblOrderDetails_tblOrders] FOREIGN KEY([OrderID])
REFERENCES [dbo].[tblOrders] ([ID])
GO
ALTER TABLE [dbo].[tblOrderDetails] CHECK CONSTRAINT [FK_tblOrderDetails_tblOrders]
GO
ALTER TABLE [dbo].[tblOrders]  WITH CHECK ADD  CONSTRAINT [FK_tblOrders_tblUsers] FOREIGN KEY([CustomerID])
REFERENCES [dbo].[tblUsers] ([Username])
GO
ALTER TABLE [dbo].[tblOrders] CHECK CONSTRAINT [FK_tblOrders_tblUsers]
GO
ALTER TABLE [dbo].[tblUsers]  WITH CHECK ADD  CONSTRAINT [FK_tblUsers_tblRoles] FOREIGN KEY([Role])
REFERENCES [dbo].[tblRoles] ([RoleID])
GO
ALTER TABLE [dbo].[tblUsers] CHECK CONSTRAINT [FK_tblUsers_tblRoles]
GO
USE [master]
GO
ALTER DATABASE [BookManagementDB] SET  READ_WRITE 
GO
