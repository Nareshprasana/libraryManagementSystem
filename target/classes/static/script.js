document.addEventListener('DOMContentLoaded', () => {
    // Handle book search form submission
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const title = document.getElementById('title').value;
            const author = document.getElementById('author').value;
            const isbn = document.getElementById('isbn').value;

            fetch(`/api/books/search?title=${title}&author=${author}&isbn=${isbn}`)
                .then(response => response.json())
                .then(data => {
                    const booksDiv = document.getElementById('books');
                    booksDiv.innerHTML = '';
                    if (data.length) {
                        data.forEach(book => {
                            const bookDiv = document.createElement('div');
                            bookDiv.innerHTML = `<strong>${book.title}</strong> by ${book.author} (ISBN: ${book.isbn})`;
                            booksDiv.appendChild(bookDiv);
                        });
                    } else {
                        booksDiv.innerHTML = '<p>No books found.</p>';
                    }
                })
                .catch(error => {
                    console.error('Error fetching search results:', error);
                });
        });
    }

    // Handle add new book form submission
    const addBookForm = document.getElementById('addBookForm');
    if (addBookForm) {
        addBookForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const title = document.getElementById('newTitle').value;
            const author = document.getElementById('newAuthor').value;
            const isbn = document.getElementById('newIsbn').value;
            const publishedDate = document.getElementById('publishedDate').value;
            const availableCopies = document.getElementById('availableCopies').value;

            fetch('/api/books', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ title, author, isbn, publishedDate, availableCopies })
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Book added successfully');
                })
                .catch(error => {
                    alert('Book added successfully');
                    console.error('Error:', error);
                });
        });
    }

    // Handle borrow book form submission
    const borrowForm = document.getElementById('borrowForm');
    if (borrowForm) {
        borrowForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const bookId = document.getElementById('bookId').value;
            const memberId = document.getElementById('memberId').value;

            fetch('/api/borrow', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ bookId, memberId })
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Error borrowing book');
                })
                .catch(error => {
                    alert('Book borrowed successfully');
                    console.error('Error:', error);
                });
        });
    }

    // Handle return book form submission
    const returnForm = document.getElementById('returnForm');
    if (returnForm) {
        returnForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const borrowId = document.getElementById('borrowId').value;

            fetch(`/api/return/${borrowId}`, {
                method: 'POST'
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Error returning book');
                })
                .catch(error => {
                    alert('Book returned successfully');
                    console.error('Error:', error);
                });
        });
    }

    // Handle add member form submission
    const addMemberForm = document.getElementById('addMemberForm');
    if (addMemberForm) {
        addMemberForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const name = document.getElementById('memberName').value;
            const phone = document.getElementById('memberPhone').value;

            fetch('/api/members', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ name, phone })
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Error adding member');
                })
                .catch(error => {
                    alert('Member added successfully');
                    console.error('Error:', error);
                });
        });
    }

    // Load members list
    const membersDiv = document.getElementById('members');
    if (membersDiv) {
        fetch('/api/members')
            .then(response => response.json())
            .then(data => {
                membersDiv.innerHTML = '';
                if (data.length) {
                    data.forEach(member => {
                        const memberDiv = document.createElement('div');
                        memberDiv.innerHTML = `${member.name} (Phone: ${member.phone})`;
                        membersDiv.appendChild(memberDiv);
                    });
                } else {
                    membersDiv.innerHTML = '<p>No members found.</p>';
                }
            })
            .catch(error => {
                console.error('Error fetching members:', error);
            });
    }
});
