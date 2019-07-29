# AppetiserCodingChallenge v2.0 (Java)
App will display a list of items obtained from an iTunes Search API and show a detailed view for each item.
<img src="https://github.com/PAYong/MovieMasterDetail/blob/master/screenshots/Screenshot_1564385579.png" width="150" height="300" />
<img src="https://github.com/PAYong/MovieMasterDetail/blob/master/screenshots/Screenshot_1564385081.png" width="500" height="300" />

## List view:
- Track Name
- Track Price
- Genre
- Image


## Detailed view:
- Image
- Track Name
- Track Price
- Genre
- Artist
- Collection name 
- Collection price 
- Country
- Description
- Heart icon when tapped will saved the item using Room and will show in Saved Items tab

## Saved Items
All the saved movies will show in Saved Items tab. 
User can unsave the movie by tapping again the Heart icon and movie will be deleted from DB and list will be updated.
	
## Architecture (MVVM)
- LiveData
Ensures that the data displayed in the user is the same with what is retrieved from the iTunes or from DB (Room).
It also notifies Observer objects when the state changes, which is what I used in the ViewModels.
- Room 
Easy to implement since it provides an abstraction layer over SQLite.
It also works with LiveData for data observation, while SQLite does not.
- ViewModel 
Allows data to survive configuration changes such as screen rotations.
ViewModel objects can also contain LifecycleObservers, such as LiveData objects.

	
	



