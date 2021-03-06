Create DATABASE SocialNetworkDB
GO
USE [SocialNetworkDB]
GO
/****** Object:  Table [dbo].[Comments]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Comments](
	[commentID] [int] IDENTITY(1,1) NOT NULL,
	[postID] [int] NULL,
	[commentEmail] [varchar](120) NULL,
	[commentContent] [nvarchar](max) NOT NULL,
	[commentTime] [datetime] NOT NULL DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[commentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[NotificationEvent]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[NotificationEvent](
	[eventType] [int] IDENTITY(0,1) NOT NULL,
	[eventName] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[eventType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Notifications]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Notifications](
	[triggerEmail] [varchar](120) NOT NULL,
	[triggeredPostID] [int] NOT NULL,
	[triggeredCommentID] [int] NULL,
	[eventType] [int] NOT NULL,
	[notifyReceivedTime] [datetime] NOT NULL DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[triggerEmail] ASC,
	[triggeredPostID] ASC,
	[eventType] ASC,
	[notifyReceivedTime] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Posts]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Posts](
	[postID] [int] IDENTITY(1,1) NOT NULL,
	[email] [varchar](120) NOT NULL,
	[title] [nvarchar](200) NOT NULL,
	[postDescription] [nvarchar](400) NOT NULL,
	[postContent] [nvarchar](max) NOT NULL,
	[images] [varchar](200) NULL,
	[submitTime] [datetime] NOT NULL DEFAULT (getdate()),
	[status] [int] NULL DEFAULT ((1)),
PRIMARY KEY CLUSTERED 
(
	[postID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PostStatus]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PostStatus](
	[postStatusID] [int] IDENTITY(1,1) NOT NULL,
	[postStatus] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[postStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Reactions]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Reactions](
	[postID] [int] NOT NULL,
	[reactEmail] [varchar](120) NOT NULL,
	[reactionType] [int] NOT NULL,
	[reactionTime] [datetime] NOT NULL DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[postID] ASC,
	[reactEmail] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[ReactionType]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[ReactionType](
	[reactionType] [int] IDENTITY(0,1) NOT NULL,
	[reactionName] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[reactionType] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserAuthentication]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserAuthentication](
	[email] [varchar](120) NOT NULL,
	[authCode] [varchar](6) NOT NULL,
	[generateTime] [datetime] NOT NULL DEFAULT (getdate()),
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[email] [varchar](120) NOT NULL,
	[password] [varchar](64) NOT NULL,
	[name] [nvarchar](200) NOT NULL,
	[status] [int] NULL CONSTRAINT [DF__Users__status__5535A963]  DEFAULT ((1)),
PRIMARY KEY CLUSTERED 
(
	[email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UserStatus]    Script Date: 9/30/2020 8:11:44 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UserStatus](
	[userStatusID] [int] IDENTITY(1,1) NOT NULL,
	[userStatus] [varchar](100) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[userStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[Comments] ON 

INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (21, 25, N'haseoleonard@gmail.com', N'Mac đẹp đếi :''>', CAST(N'2020-09-30 17:11:28.143' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (22, 26, N'haseoleonard@gmail.com', N'Mạng xịn dư lày :''<', CAST(N'2020-09-30 17:12:15.000' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (23, 25, N'nghiadhse140362@fpt.edu.vn', N'Đẹp là rõ :''<', CAST(N'2020-09-30 17:15:10.780' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (24, 26, N'nghiadhse140362@fpt.edu.vn', N'okhe đọ', CAST(N'2020-09-30 17:15:26.773' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (25, 27, N'haseoleonard@gmail.com', N'vẽ dư lày mất hết :(', CAST(N'2020-09-30 19:08:49.807' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (26, 31, N'haseoleonard@gmail.com', N':( chả có gì đâu mà share :( ', CAST(N'2020-09-30 19:17:34.563' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (27, 30, N'haseoleonard@gmail.com', N'Ngôn ngữ nhựt FPTU Cần Ther <3', CAST(N'2020-09-30 19:18:12.990' AS DateTime))
INSERT [dbo].[Comments] ([commentID], [postID], [commentEmail], [commentContent], [commentTime]) VALUES (28, 30, N'haseoleonard@gmail.com', N'mlem mlem nom nom', CAST(N'2020-09-30 19:28:03.813' AS DateTime))
SET IDENTITY_INSERT [dbo].[Comments] OFF
SET IDENTITY_INSERT [dbo].[NotificationEvent] ON 

INSERT [dbo].[NotificationEvent] ([eventType], [eventName]) VALUES (2, N'Comment')
INSERT [dbo].[NotificationEvent] ([eventType], [eventName]) VALUES (1, N'Dislike')
INSERT [dbo].[NotificationEvent] ([eventType], [eventName]) VALUES (0, N'Like')
SET IDENTITY_INSERT [dbo].[NotificationEvent] OFF
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 25, NULL, 0, CAST(N'2020-09-30 17:11:54.763' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 25, 21, 2, CAST(N'2020-09-30 17:11:28.153' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 27, NULL, 1, CAST(N'2020-09-30 19:08:32.920' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 27, 25, 2, CAST(N'2020-09-30 19:08:49.817' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 28, NULL, 1, CAST(N'2020-09-30 19:18:31.433' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 29, NULL, 0, CAST(N'2020-09-30 19:18:24.230' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 30, NULL, 0, CAST(N'2020-09-30 19:17:53.590' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 30, 27, 2, CAST(N'2020-09-30 19:18:12.993' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 30, 28, 2, CAST(N'2020-09-30 19:28:03.817' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 31, NULL, 0, CAST(N'2020-09-30 19:17:12.270' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'haseoleonard@gmail.com', 31, 26, 2, CAST(N'2020-09-30 19:17:34.567' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'nghiadhse140362@fpt.edu.vn', 26, NULL, 0, CAST(N'2020-09-30 17:15:21.353' AS DateTime))
INSERT [dbo].[Notifications] ([triggerEmail], [triggeredPostID], [triggeredCommentID], [eventType], [notifyReceivedTime]) VALUES (N'nghiadhse140362@fpt.edu.vn', 26, 24, 2, CAST(N'2020-09-30 17:15:26.780' AS DateTime))
SET IDENTITY_INSERT [dbo].[Posts] ON 

INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (25, N'nghiadhse140362@fpt.edu.vn', N'Bài viết đầu tiên của server', N'Chỉ là 1 bài viết rất chi bình thường luôn á :(', N'lululululu Không có gì trong này đâu mọi ngừi đừng để ý :(<br/><br/>', N'1601460404454.jpg', CAST(N'2020-09-30 17:06:46.250' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (26, N'haseoleonard@gmail.com', N'Second Post On Server', N'This is a very detailed Post', N'Don''t even try to do something with this post :(', N'1601460664870.png', CAST(N'2020-09-30 17:11:05.077' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (27, N'nghiadhse140362@fpt.edu.vn', N'Một Chiếc Usecase Diagram sai hẳn :(', N'sai lắm luôn á ', N'Thì chung quy lại vẫn là sai ? lululul', N'1601467623021.png', CAST(N'2020-09-30 19:07:04.060' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (28, N'nghiadhse140362@fpt.edu.vn', N':PepeCryingWithAGun', N'pắn cho bây h ;_;', N'share meme material :( khong biết share gì nữa luôn', N'1601467939054.png', CAST(N'2020-09-30 19:12:19.580' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (29, N'nghiadhse140362@fpt.edu.vn', N'Cayyyyyyyyyyyyyy', N':pepeReeeeeeeeeeeeeeeee', N'Cayyyy á !!!!!!!!!!!!!!!!!!!', N'1601467996662.png', CAST(N'2020-09-30 19:13:16.850' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (30, N'nghiadhse140362@fpt.edu.vn', N'Ảnh của 1 bé nào đó ;_;', N'Vừa xưn vừa giỏi nhựt nhóe', N'Info thì hong thể share ròi :''> FPTU Cần ther nhóe :3', N'1601468095893.jpg', CAST(N'2020-09-30 19:14:57.183' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (31, N'nghiadhse140362@fpt.edu.vn', N'1 Post chia sẻ bé tẹo tèo teo', N'Vào nói chuyện cho đủ số :(', N'Vào chia sẻ đi nào các bạn :(', NULL, CAST(N'2020-09-30 19:16:22.580' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (32, N'haseoleonard@gmail.com', N'1 chiếc Database Diagram Be bé', N'[LAB231] Social Network', N'Thề luôn bé lắm á :( hong có cunkthunk gì đâu :(', N'1601468848371.jpg', CAST(N'2020-09-30 19:27:28.967' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (33, N'haseoleonard@gmail.com', N'Chút Ảnh cúi ngày giải tỏa cunk thunk', N'Just a Meow Pic', N'Nhìn như cái đùi gà dzị á ;_;', N'1601469226537.JPG', CAST(N'2020-09-30 19:33:47.690' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (34, N'haseoleonard@gmail.com', N'Chữ ải chữ ai :(', N'Ai biết thì yên lặng nhé', N':( cơ mà k biết thì cũng cấm chê nhé kẻo k ra đượt trường âu :''<', N'1601469528507.png', CAST(N'2020-09-30 19:38:48.950' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (35, N'haseoleonard@gmail.com', N'Some more catpics', N'1 chút mòe cho ngày thêm đẹp', N'Mòe và chân của 1 cóc bạc FPTU nào đó :( ', N'1601469606492.jpg', CAST(N'2020-09-30 19:40:10.350' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (36, N'haseoleonard@gmail.com', N'Meme Share (Again)', N'1 Chút Meme giải tỏa he', N'các bạn test Bài thì siêu cấp nghiêm túc :(<br/>Còn đây là toi: ', N'1601469840196.jpeg', CAST(N'2020-09-30 19:44:00.717' AS DateTime), 1)
INSERT [dbo].[Posts] ([postID], [email], [title], [postDescription], [postContent], [images], [submitTime], [status]) VALUES (37, N'haseoleonard@gmail.com', N'Chuyên Mục Show Ảnh', N'Cafe cùng những ngừi pạn :''<', N'Làm chút cafe bờ hồ gươm cho chill đi các pạn <br/>Location: Cafe Đinh - Hồ Gươm - Hà Nội', N'1601470009618.JPG', CAST(N'2020-09-30 19:46:58.390' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[Posts] OFF
SET IDENTITY_INSERT [dbo].[PostStatus] ON 

INSERT [dbo].[PostStatus] ([postStatusID], [postStatus]) VALUES (1, N'ACTIVE')
INSERT [dbo].[PostStatus] ([postStatusID], [postStatus]) VALUES (2, N'DELETED')
SET IDENTITY_INSERT [dbo].[PostStatus] OFF
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (25, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 17:11:54.760' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (25, N'nghiadhse140362@fpt.edu.vn', 0, CAST(N'2020-09-30 17:06:53.653' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (26, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 17:11:10.727' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (26, N'nghiadhse140362@fpt.edu.vn', 0, CAST(N'2020-09-30 17:15:21.353' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (27, N'haseoleonard@gmail.com', 1, CAST(N'2020-09-30 19:08:32.913' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (27, N'nghiadhse140362@fpt.edu.vn', 1, CAST(N'2020-09-30 19:07:16.420' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (28, N'haseoleonard@gmail.com', 1, CAST(N'2020-09-30 19:18:31.430' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (29, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 19:18:24.227' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (29, N'nghiadhse140362@fpt.edu.vn', 0, CAST(N'2020-09-30 19:13:21.003' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (30, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 19:17:53.590' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (30, N'nghiadhse140362@fpt.edu.vn', 0, CAST(N'2020-09-30 19:15:02.220' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (31, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 19:17:12.270' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (31, N'nghiadhse140362@fpt.edu.vn', 0, CAST(N'2020-09-30 19:16:25.620' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (32, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 19:27:40.517' AS DateTime))
INSERT [dbo].[Reactions] ([postID], [reactEmail], [reactionType], [reactionTime]) VALUES (35, N'haseoleonard@gmail.com', 0, CAST(N'2020-09-30 19:40:20.970' AS DateTime))
SET IDENTITY_INSERT [dbo].[ReactionType] ON 

INSERT [dbo].[ReactionType] ([reactionType], [reactionName]) VALUES (1, N'Dislike')
INSERT [dbo].[ReactionType] ([reactionType], [reactionName]) VALUES (0, N'Like')
SET IDENTITY_INSERT [dbo].[ReactionType] OFF
INSERT [dbo].[UserAuthentication] ([email], [authCode], [generateTime]) VALUES (N'haseoleonard@gmail.com', N'EWGVWM', CAST(N'2020-09-30 17:08:56.463' AS DateTime))
INSERT [dbo].[UserAuthentication] ([email], [authCode], [generateTime]) VALUES (N'huunghia26298@gmail.com', N'9LWOVG', CAST(N'2020-09-30 17:13:56.943' AS DateTime))
INSERT [dbo].[UserAuthentication] ([email], [authCode], [generateTime]) VALUES (N'LongBNHSE140368@fpt.edu.vn', N'S4GW26', CAST(N'2020-09-30 17:13:19.200' AS DateTime))
INSERT [dbo].[UserAuthentication] ([email], [authCode], [generateTime]) VALUES (N'nghiadhse140362@fpt.edu.vn', N'GNVPK4', CAST(N'2020-09-30 17:04:07.313' AS DateTime))
INSERT [dbo].[Users] ([email], [password], [name], [status]) VALUES (N'haseoleonard@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'涅炎', 2)
INSERT [dbo].[Users] ([email], [password], [name], [status]) VALUES (N'huunghia26298@gmail.com', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Account Email Của Server :(', 1)
INSERT [dbo].[Users] ([email], [password], [name], [status]) VALUES (N'LongBNHSE140368@fpt.edu.vn', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Bùi Nguyễn Hoàng Long', 1)
INSERT [dbo].[Users] ([email], [password], [name], [status]) VALUES (N'nghiadhse140362@fpt.edu.vn', N'e423d8071db43917b0863f4967487c6422ed7b588e2a7135ee9fcf1348e82711', N'Đào Hữu Nghĩa', 2)
SET IDENTITY_INSERT [dbo].[UserStatus] ON 

INSERT [dbo].[UserStatus] ([userStatusID], [userStatus]) VALUES (2, N'AUTHENTICATED')
INSERT [dbo].[UserStatus] ([userStatusID], [userStatus]) VALUES (1, N'NEW')
SET IDENTITY_INSERT [dbo].[UserStatus] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Notifica__53C051E08A79C4DE]    Script Date: 9/30/2020 8:11:44 PM ******/
ALTER TABLE [dbo].[NotificationEvent] ADD UNIQUE NONCLUSTERED 
(
	[eventName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__PostStat__6098883A4D8F666C]    Script Date: 9/30/2020 8:11:44 PM ******/
ALTER TABLE [dbo].[PostStatus] ADD UNIQUE NONCLUSTERED 
(
	[postStatus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__Reaction__C0DD0DD522F5C0A9]    Script Date: 9/30/2020 8:11:44 PM ******/
ALTER TABLE [dbo].[ReactionType] ADD UNIQUE NONCLUSTERED 
(
	[reactionName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
SET ANSI_PADDING ON

GO
/****** Object:  Index [UQ__UserStat__E54B7F6FAEF67EA3]    Script Date: 9/30/2020 8:11:44 PM ******/
ALTER TABLE [dbo].[UserStatus] ADD UNIQUE NONCLUSTERED 
(
	[userStatus] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([commentEmail])
REFERENCES [dbo].[Users] ([email])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Comments]  WITH CHECK ADD FOREIGN KEY([postID])
REFERENCES [dbo].[Posts] ([postID])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([eventType])
REFERENCES [dbo].[NotificationEvent] ([eventType])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([triggerEmail])
REFERENCES [dbo].[Users] ([email])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([triggeredPostID])
REFERENCES [dbo].[Posts] ([postID])
GO
ALTER TABLE [dbo].[Notifications]  WITH CHECK ADD FOREIGN KEY([triggeredCommentID])
REFERENCES [dbo].[Comments] ([commentID])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Posts]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Users] ([email])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[Posts]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[PostStatus] ([postStatusID])
GO
ALTER TABLE [dbo].[Reactions]  WITH CHECK ADD FOREIGN KEY([postID])
REFERENCES [dbo].[Posts] ([postID])
GO
ALTER TABLE [dbo].[Reactions]  WITH CHECK ADD FOREIGN KEY([reactEmail])
REFERENCES [dbo].[Users] ([email])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Reactions]  WITH CHECK ADD FOREIGN KEY([reactionType])
REFERENCES [dbo].[ReactionType] ([reactionType])
GO
ALTER TABLE [dbo].[UserAuthentication]  WITH CHECK ADD FOREIGN KEY([email])
REFERENCES [dbo].[Users] ([email])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD FOREIGN KEY([status])
REFERENCES [dbo].[UserStatus] ([userStatusID])
GO
