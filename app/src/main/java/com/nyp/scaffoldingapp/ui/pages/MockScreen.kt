package practice.wk03

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Week 3 practice — build the screen in materials/wk03/mockup-brief.md
 *
 *  ┌────────────────────────────────────────────┐
 *  │  ( ◉ )  Weekly Planner              ♡      │  1. header
 *  │         14 tasks this week                 │
 *  │                                            │
 *  │   ┌────────┬────────┬────────┐             │  2. stats row
 *  │   │   8    │   4    │   2    │             │
 *  │   │  done  │ active │overdue │             │
 *  │   └────────┴────────┴────────┘             │
 *  │                                            │
 *  │  Today                                     │  3. heading
 *  │  ────────────────────────────────────────  │
 *  │  ●  Submit A1 draft            4 Dec        │  4. three items,
 *  │  ●  Read the rubric            2 Dec        │     same layout
 *  │  ●  Set up the emulator        1 Dec        │
 *  │                                            │
 *  │  [  Add task  ]   [  View all  ]           │  5. two buttons
 *  └────────────────────────────────────────────┘
 *
 * Do the TODOs in order. Preview after each one.
 */
@Composable
fun MockScreen(modifier: Modifier = Modifier) {

    // TODO 6: the heart needs to remember whether it is filled.
    //         Declare that state here. (One line. We come back to it.)

    Column(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // ---- 1. Header -------------------------------------------------
        // A Row containing: a circle, then a Column of title + subtitle,
        // then the heart pushed to the far right.
        //
        // TODO 1a: make the Box below circular and give it a colour.
        //          Hint: Modifier.size(...).clip(CircleShape).background(...)
        // TODO 1b: put the title and subtitle in a Column beside it.
        //          Use MaterialTheme.typography — no hardcoded sp.
        // TODO 1c: push the heart to the right edge.
        //          Hint: Modifier.weight(1f) on the part that should stretch.
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(color = Color.Red))                     // ← the avatar
            Spacer(Modifier.width(12.dp))

            Column() {
                Text("Weekly Planner", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text("14 tasks this week", style = MaterialTheme.typography.titleSmall)
            }

            Spacer(Modifier.width(24.dp))

            var filled by remember {mutableStateOf(false)}

            if (filled == false) {
                Text("♡", Modifier.clickable { filled = true })
            }
            else{
                Text("♥", Modifier.clickable { filled = false })
            }
        }

        Spacer(Modifier.height(24.dp))

        // ---- 2. Stats row ----------------------------------------------
        // Three stats sharing the width evenly.
        //
        // TODO 2: give each Stat a Modifier.weight(1f) so they share the row.
        //         Try it without weight first, and look at what happens.
        Row(Modifier.fillMaxWidth()) {
            Stat(value = "8", label = "done", Modifier.weight(1f))
            Stat(value = "4", label = "active", Modifier.weight(1f))
            Stat(value = "2", label = "overdue", Modifier.weight(1f))
        }

        Spacer(Modifier.height(24.dp))

        // ---- 3. Section heading ----------------------------------------
        // TODO 3: a heading, then a divider under it.
        //         Hint: HorizontalDivider()

        Text("Today", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(5.dp))
        HorizontalDivider()
        Spacer(Modifier.height(5.dp))

        // ---- 4. Three items --------------------------------------------
        // TODO 4: three TaskRow calls with different content.
        //         Write them out by hand. Yes, all three. You will find out
        //         in Week 4 why that stops being a good idea.

        TaskRow(title = "Submit A1 draft", date = "4 Dec")
        Spacer(Modifier.height(5.dp))     // pushes the buttons to the bottom
        TaskRow(title = "Read the rubric", date = "2 Dec")
        Spacer(Modifier.height(5.dp))   // pushes the buttons to the bottom
        TaskRow(title = "Set up the emulator", date = "1 Dec")
        Spacer(Modifier.height(5.dp))    // pushes the buttons to the bottom

        Spacer(Modifier.weight(1f))
        // ---- 5. Two buttons --------------------------------------------
        // TODO 5: two buttons side by side, sharing the width.
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(
                onClick = {},
                content = { Text("Add Task") },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.weight(0.1f))
            Button(
                onClick = {},
                content = { Text("View All") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/** One statistic: a big number over a small label. */
@Composable
private fun Stat(value: String, label: String, modifier: Modifier = Modifier) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.headlineSmall)
        Text(label, style = MaterialTheme.typography.bodySmall)
    }
}

/** One row in the list: a dot, a title that flexes, and a date. */
@Composable
private fun TaskRow(title: String, date: String, modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(color = Color.Black))
        Spacer(modifier = Modifier.width(12.dp))
        Text(title)
        Spacer(Modifier.weight(1f))
        Text(date)
    }
    // TODO 4b: a Row with a small circular dot, the title, and the date
    //          on the far right. Same weight(1f) trick as the header.
}

@Composable
private fun HeartClick() {

}

// ---- 6. Previews ---------------------------------------------------------
// TODO: a second preview showing a DIFFERENT state — try the empty version,
//       or a very long title. Two previews of the same composable is normal.
@Preview(showBackground = true)
@Composable
private fun MockScreenPreview() {
    MaterialTheme { MockScreen() }
}
