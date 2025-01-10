#### 팀원:윤태우,윤찬영,박현지,이호연

# 게시판 

## Body parameter

- title
- createdTime 
- updatedTime
- posts

### 생성 Post
- /boards

- Body : String title

### 조회 Get
- /boards (응답데이터에 id 포함)
  게시판 모든 목록 조회

- /boards/{boardId} (특정게시판의 게시글 목록 조회)
- **** 게시글을 구현하고 난뒤 구현해야함

### 수정  Put
- /boards/{boardId}
- id : long -> 게시판 ID
- Body : title (String)

### 삭제  Delete
- /boards/{boardId}
- id : 게시판 ID

# 게시글 

## Body parameter

- title 
- content 
- board 
- createdTime 
- updatedTime
- writer
- comments 

### 생성 Post
- /post/{boardId} (해당 게시판 아이디)
- Body : title (String) , content(String) , writer(String)


### 조회 Get
- /post/numberofcomments  (게시글 목록 조회, 응답데이터에 게시글 id,댓글 개수 포함)
- /post/{postId}  (상세조회,댓글 목록 포함)
- /post/filtercomments/{postId} (특정게시글의 댓글목록 조회)

### 수정 Put
- /posts/{postId} (해당 포스트의 내용 수정)
- Body : title (String) , content(String) , writer(String)

### 삭제 Delete
- /posts/{postId}

# 댓글 

## Body parameter

- writer 
- content 
- post 
- createdTime 
- updatedTime

### 생성 Post
- /comments/{postId} (해당 게시글 아이디)
- Body : writer (String), content (String) 

### 수정  Put
- /comments/{commentId}
- Body : writer (String), content (String)

### 삭제  Delete
- /comments/{commentId}
