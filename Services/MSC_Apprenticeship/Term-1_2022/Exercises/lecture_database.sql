import sqlite3


conn = sqlite3.connect('chinook.db')
c = conn.cursor()

#How many times was the track ‘Not The Doctor’ invoiced?
c.execute("""SELECT COUNT(InvoiceLineId) FROM invoice_items
WHERE TrackId = (SELECT TrackId FROM tracks WHERE Name = 'Not The Doctor')""")
print(c.fetchall())

#What is the average track length in each genre?
c.execute("""SELECT genres.Name, AVG(tracks.Milliseconds) / 60000
FROM genres
INNER JOIN tracks ON tracks.GenreId = genres.GenreId
GROUP BY tracks.GenreId
ORDER BY AVG(tracks.Milliseconds) DESC""")
print(c.fetchall())


#How many tracks from each genres are in the ‘90’s Music’ playlist?
c.execute("""SELECT DISTINCT genres.Name, COUNT(genres.GenreId)
FROM tracks
INNER JOIN playlist_track ON tracks.TrackId = playlist_track.TrackId
INNER JOIN genres ON tracks.GenreId = genres.GenreId
WHERE playlist_track.PlaylistId = (SELECT PlaylistId FROM playlists WHERE Name = '90’s Music')
GROUP BY genres.GenreId
ORDER BY COUNT(genres.GenreId) DESC
""")
print(c.fetchall())


##Which album has the most tracks?
c.execute("""SELECT album_title, MAX(track_count)
FROM
(SELECT albums.Title album_title, COUNT(tracks.TrackId) track_count
FROM albums
INNER JOIN tracks ON tracks.AlbumId = albums.AlbumId
GROUP BY albums.AlbumId)""")
print(c.fetchall())

conn.close()
