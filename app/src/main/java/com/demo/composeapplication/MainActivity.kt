package com.demo.composeapplication

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.demo.composeapplication.data.CardContent
import com.demo.composeapplication.data.ColumnContent
import com.demo.composeapplication.data.ImageContent
import com.demo.composeapplication.data.TextAndImageContent
import com.demo.composeapplication.data.TextContent
import com.demo.composeapplication.ui.theme.ComposeApplicationTheme
import com.demo.composeapplication.ui.theme.Pink80
import com.demo.composeapplication.viewmodel.MainVM
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Compose")
                    //Counter()
                    MainUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI() {
    var menuState by remember {
        mutableStateOf(false)
    }

    var selectedItem by remember {
        mutableIntStateOf(0)
    }
    val rememberScope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    val navigationDrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    ModalNavigationDrawer(scrimColor = Color.Red, drawerState = navigationDrawerState, drawerContent = {
        ModalDrawerSheet {
            NavigationDrawerItem(label = { Text(text = "Home")}, icon = {
                                                                        Icon(
                                                                            imageVector = Icons.Outlined.Home,
                                                                            contentDescription =  "Home"
                                                                        )
            }, selected = false, onClick = {  })
            NavigationDrawerItem(label = { Text(text = "Favourite")}, icon = {
                Icon(
                    imageVector = Icons.Outlined.Favorite,
                    contentDescription =  "Favourite"
                )
            }, selected = false, onClick = {  })
            NavigationDrawerItem(label = { Text(text = "Profile")}, icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription =  "Home"
                )
            }, selected = false, onClick = {  })
            NavigationDrawerItem(label = { Text(text = "Setting")}, icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription =  "Home"
                )
            }, selected = true, onClick = {  }, badge = {
                Badge {
                    Text(text = "+1")
                }
            }, colors = NavigationDrawerItemDefaults.colors(selectedTextColor = Color.Red,
                selectedIconColor = Color.Red))
        }
    }) {
        Scaffold(snackbarHost = {
            SnackbarHost(snackbarHostState) {data ->
                Snackbar(containerColor = Color.Red, actionColor = Color.Green, snackbarData = data)
            }
        }, topBar = {
            LargeTopAppBar(title = { Text(text = "Counter") }, navigationIcon = {
                IconButton(onClick = {
                    rememberScope.launch {
                        navigationDrawerState.open()
                    }
                }) {
                    Icon(
                        imageVector = Icons.Outlined.Menu,
                        contentDescription = "Open Navigation Drawer"
                    )
                }
            }, actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.AccountCircle, contentDescription = "Account")
                }
                IconButton(onClick = { menuState = !menuState }) {
                    Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "More options")
                }
                DropdownMenu(expanded = menuState, onDismissRequest = { menuState = false }) {
                    DropdownMenuItem(text = { Text("Help") }, onClick = { })
                    DropdownMenuItem(text = { Text("More Settings") }, onClick = { })
                }
            }, scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior())
        }, bottomBar = {
            BottomAppBar(containerColor = Color.Red, contentPadding = PaddingValues(10.dp), actions = {
                IconButton(onClick = {  }) {
                    Icon(imageVector = Icons.Outlined.Home, contentDescription = "Home")
                }
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Account")

                }
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Outlined.Settings, contentDescription = "Settings")
                }
            }, floatingActionButton = {
                FloatingActionButton(onClick = {  }) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
                }
            })
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                rememberScope.launch {
                    val snackbarResult = snackbarHostState.showSnackbar(message = "Perform action on click",
                        actionLabel = "Retry", duration = SnackbarDuration.Long)
                    if(snackbarResult == SnackbarResult.ActionPerformed) {
                        snackbarHostState.showSnackbar(message = "Congratulation")
                    }
                }
            }) {
                Icon(imageVector = Icons.Outlined.Add, contentDescription = "Add")
            }
        }, floatingActionButtonPosition = FabPosition.Center) { paddingValue ->
            Counter(Modifier.padding(paddingValue))
        }
    }

}

val countListSaver = listSaver<Count, Int>(
    save = { listOf(it.currentValue) },
    restore = { Count(currentValue = it[0])}

)

val mapCountSaver = mapSaver<Count>(
    save = { mapOf("count" to it.currentValue) },
    restore = { Count(currentValue = it["count"] as Int) }
)

@Composable
fun Counter(modifier: Modifier) {
    val mainVM: MainVM = viewModel()
    val currentCount = mainVM.countLD.observeAsState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        HeaderUI { currentCount.value ?: 0 }
        ActionUI {
            mainVM.changeCount(it)
        }
    }
}

@Composable
fun ActionUI(action: (Boolean) -> Unit) {
    Row {
        Button(
            onClick = {
                action(true)
            }, modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(text = "Add")
        }
        Button(
            onClick = {
                action(false)
            }, modifier = Modifier
                .weight(1f)
                .padding(5.dp)
        ) {
            Text(text = "Subtract")
        }
    }
}

@Composable
fun HeaderUI(countLD: () -> Int) {
    Text(text = "${countLD()}", fontSize = TextUnit(48f, TextUnitType.Sp))
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.user), contentDescription = "User",
            Modifier
                .width(dimensionResource(id = R.dimen.user_image_size))
                .height(64.dp)
        )
        Column {
            Text(
                text = stringResource(id = R.string.app_name),
                color = colorResource(id = R.color.purple_500)
            )
            Text(
                text = "User Information",
                color = Pink80
            )
        }
    }
}

@Composable
fun HorizontalList() {
    val userList: List<User> = remember {
        mutableListOf<User>().apply {
            add(User(1, "Sachin", Icons.Outlined.Person))
            add(User(2, "Rahul", Icons.Outlined.Person))
            add(User(3, "Kapil", Icons.Outlined.Person))
            add(User(4, "Sourav", Icons.Outlined.Person))
            add(User(5, "Rohit", Icons.Outlined.Person))
            add(User(6, "Virat", Icons.Outlined.Person))
            add(User(7, "Anil", Icons.Outlined.Person))
            add(User(8, "Messi", Icons.Outlined.Person))
            add(User(9, "Ronalda", Icons.Outlined.Person))
            add(User(10, "Shubhman", Icons.Outlined.Person))
            add(User(11, "Harshdeep", Icons.Outlined.Person))
            add(User(12, "Shami", Icons.Outlined.Person))
            add(User(13, "Ricky", Icons.Outlined.Person))
            add(User(14, "Mark", Icons.Outlined.Person))
            add(User(15, "Peter", Icons.Outlined.Person))
            add(User(16, "David", Icons.Outlined.Person))
        }
    }

    val listState = rememberLazyListState()
    val rememberScope = rememberCoroutineScope()

    Column {

        LazyRow(state = listState, contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)){
            item {
                Text(text = "Team List")
            }
            items(items = userList, key = { it.userId}) {
                UserItem(it)
            }
            item {
                Text(text = "For match on 23rd Jan 2024", modifier = Modifier.clickable(onClick = {
                    rememberScope.launch {
                        listState.scrollToItem(0)
                    }
                }))
            }
        }

        LazyHorizontalGrid(
            modifier = Modifier.height(120.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            rows = GridCells.Fixed(3)){
            items(items = userList, key = { it.userId}) {
                if(it.userId == 5) {
                    CaptainItem(it)
                } else {
                    UserItem(it)
                }
            }
        }

        LazyHorizontalStaggeredGrid(
            modifier = Modifier.height(120.dp),
            rows = StaggeredGridCells.Fixed(3),
            contentPadding = PaddingValues(16.dp),
            horizontalItemSpacing = 16.dp,
            verticalArrangement = Arrangement.spacedBy(16.dp),) {
            items(items = userList, key = { it.userId}) {
                    UserItem(it)
            }
        }
    }

}

data class Captain(val id: Int, val name: String)



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DisplayVerticalList() {
    val userList: List<ColumnContent> = remember {
        mutableListOf<ColumnContent>().apply {
            add(TextAndImageContent(User(1, "Sachin\nTendulkar\nis\nbest\ncricketer\nHe\nis\nmaster blaster", Icons.Outlined.Person)))
            add(TextContent(User(2, "Rahul Dravid", Icons.Outlined.Person)))
            add(TextContent(User(3, "Sourav", Icons.Outlined.Person)))
            add(TextContent(User(4, "MSD", Icons.Outlined.Person)))
            add(TextContent(User(5, "Gautam", Icons.Outlined.Person)))
            add(TextContent(User(6, "Virat", Icons.Outlined.Person)))
            add(ImageContent(User(7, "Virat", Icons.Outlined.Favorite)))
            add(TextContent(User(8, "Virat", Icons.Outlined.AccountBox)))
            add(TextContent(User(9, "Virat", Icons.Outlined.Home)))
            add(CardContent(User(10, "Shami", Icons.Outlined.Home)))
            add(CardContent(User(11, "Ashwin", Icons.Outlined.Home)))
            add(CardContent(User(12, "Jadega", Icons.Outlined.Home)))
            add(CardContent(User(13, "Yuvrag", Icons.Outlined.Home)))
        }
    }
    val listState = rememberLazyListState()
    val rememberScope = rememberCoroutineScope()

    LazyColumn {
        items(items =  userList, key = {it.getId()}, contentType = {ColumnContent::class.java}){
            it.BuildItem {

            }
        }
    }

   /* LazyVerticalStaggeredGrid(columns =StaggeredGridCells.FixedSize(200.dp)) {
        items(items = userList, key = {it.userId}) { user ->
            CardUserListItem(user)
        }
    }*/

    /*Box {
        LazyColumn(state = listState, contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 16.dp,
            bottom = 64.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            userList.forEachIndexed { index, user ->
                if(index == 0 || userList[index].userName.first() != userList[index-1].userName.first()) {
                    stickyHeader {
                        Text("" + user.userName.first(), modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(16.dp))
                    }
                }
                item(key = user.userId) {
                    CardUserListItem(user)
                }
            }
            *//*  item {
                  Text("Team List")
              }

              items(items = userList, key = {it.userId}) {
                  CardUserListItem(it)
              }
              item {
                  Text("For 2024")
              }*//*
        }
        val fabVisibility = remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }
        if(fabVisibility.value) {
            FloatingActionButton(onClick = {
                rememberScope.launch {
                    listState.scrollToItem(0)
                }
            }, shape = FloatingActionButtonDefaults.smallShape,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)) {
                Icon(imageVector = Icons.Outlined.KeyboardArrowUp, contentDescription = "Scroll To Bottom")
            }
        }
    }*/

}

@Composable
private fun CardUserListItem(it: User) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(imageVector = it.userImage, contentDescription = it.userName)
            Text(text = it.userName, modifier = Modifier.padding(8.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CaptainItem(user: User) {
    Row {
        Icon(imageVector = user.userImage, contentDescription = user.userName)
        Text(text = user.userName, modifier = Modifier.padding(start = 8.dp))
        Badge {
            Text(text = "C")
        }
    }
}

@Composable
fun UserItem(user: User) {
    Row {
        Icon(imageVector = user.userImage, contentDescription = user.userName)
        Text(text = user.userName, modifier = Modifier.padding(start = 8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHorizontalList() {
    HorizontalList()
}

@Preview(showBackground = true)
@Composable
fun PreviewVerticallList() {
    DisplayVerticalList()
}


data class User(val userId: Int, val userName: String, val userImage: ImageVector)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        //Greeting("Compose")
        MainUI()
    }
}

@Parcelize
data class Count(val currentValue: Int) : Parcelable