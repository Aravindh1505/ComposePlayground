package com.aravindh.composeplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aravindh.composeplayground.ui.theme.ComposePlaygroundTheme

val experiences = listOf<Experience>(
    Experience(
        role = "Senior Application Consultant - Android",
        company = "Optimum Solutions Pte Ltd",
        duration = "Sep 2023 - Present · 1 yr 9 mos",
        description = "\u2022 Led the transformation of the OCBC Bank Business Android application by transitioning Java classes into Kotlin; optimized Local and Overseas Transfer modules, enhancing code quality by 35%."
    ),
    Experience(
        role = "Staff Engineer - Product and Platform Engineering",
        company = "Altimetrik",
        duration = "Oct 2021 - Sep 2023 · 2 yrs",
        description = "\u2022 Enhanced the FordPass Android app's Vehicle Details module by implementing customer PII data masking, resulting in a 25% reduction in data breach incidents and increased customer trust."
    ),
    Experience(
        role = "Software Engineer",
        company = "Object Frontier Software",
        duration = "Feb 2020 - Oct 2021 · 1 yr 9 mos",
        description = "\u2022 Introduced security enhancements for Axis Mobile Banking through the implementation of code optimization, shrinking, and obfuscation techniques resulting in a notable 15% improvement in Android application security levels"
    ),
    Experience(
        role = "Software Engineer",
        company = "Tvs Next",
        duration = "Dec 2017 - Feb 2020 · 2 yrs 3 mos",
        description = "\u2022 Executed the seamless implementation of third-party libraries, including Razorpay payment gateway, Sigtcall for Video call consultation, Material design components, FCM notification, and Google Analytics, to elevate the Health Care domain (MediBuddy vHealth) user experience and engagement levels."
    )
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MyProfile()
                }
            }
        }
    }
}

@Composable
fun MyProfile() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)

        LazyColumn(
            horizontalAlignment = Alignment.Start
        ) {
            item {
                ProfilePhoto()

                // Top Section
                Header(text = stringResource(R.string.profile_name), modifier = modifier)
                Body(text = stringResource(R.string.header_section))
                Body(text = stringResource(R.string.current_company), modifier = Modifier.padding(top = 8.dp))
                Paragraph(text = stringResource(R.string.current_location))

                // About Section
                CustomSpacer()
                Header(stringResource(R.string.about))
                Body(stringResource(R.string.about_section), modifier = modifier)

                // Experience Section
                Header(stringResource(R.string.experience), modifier)
            }

            items(experiences) { experience ->
                Title(experience.role)
                Body(experience.company)
                Paragraph(experience.duration)
                Body(experience.description, modifier = modifier)
                CustomSpacer()
            }
        }

    }
}

@Composable
fun ProfilePhoto() {
    Image(
        painterResource(R.drawable.profile),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Header(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Bold
    )
}


@Composable
fun Body(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium
    )
}

@Composable
fun Paragraph(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall
    )
}

@Composable
fun CustomSpacer() {
    Spacer(modifier = Modifier.height(16.dp))
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposePlaygroundTheme {
        MyProfile()
    }
}