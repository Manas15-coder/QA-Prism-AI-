document.addEventListener(
    "DOMContentLoaded",
    () => {

        const form =
            document.getElementById(
                "analysisForm");

        if (form) {

            const fileInput =
                document.getElementById(
                    "prdFile");

            const fileName =
                document.getElementById(
                    "fileName");

            const analyzeBtn =
                document.getElementById(
                    "analyzeBtn");

            const loadingSection =
                document.getElementById(
                    "loadingSection");

            const statusText =
                document.getElementById(
                    "statusText");

            fileInput.addEventListener(
                "change",
                event => {

                    const file =
                        event.target.files[0];

                    if (!file) {

                        fileName.innerText =
                            "No file selected";

                        return;
                    }

                    fileName.innerText =
                        "Selected File: "
                        + file.name;
                });

            form.addEventListener(
                "submit",
                () => {

                    analyzeBtn.disabled =
                        true;

                    analyzeBtn.innerText =
                        "Uploading PRD...";

                    loadingSection.style.display =
                        "block";

                    const messages = [

                        "Extracting PRD Content...",

                        "Validating Requirements...",

                        "Saving PRD Session...",

                        "Preparing QA Workspace..."
                    ];

                    let index = 0;

                    const interval =
                        setInterval(
                            () => {

                                if (
                                    index <
                                    messages.length
                                ) {

                                    statusText.innerText =
                                        messages[index];

                                    index++;

                                } else {

                                    clearInterval(
                                        interval);
                                }

                            },
                            1500);
                });
        }
    });

async function generateArtifact(
    action,
    button) {

    const output =
        document.getElementById(
            "output");

    const loadingSection =
        document.getElementById(
            "loadingSection");

    const statusText =
        document.getElementById(
            "statusText");

    document
        .querySelectorAll(
            ".artifact-btn")
        .forEach(
            btn => btn.disabled = true);

    loadingSection.style.display =
        "block";

    statusText.innerText =
        "Generating "
        + action
        + "...";

    output.innerHTML =
        `
        <h3>${action}</h3>
        <p>Generating response...</p>
        `;

    try {

        const response =
            await fetch(
                "/generate?action="
                + encodeURIComponent(
                    action),
                {
                    method: "POST"
                });

        const result =
            await response.text();

        output.innerHTML =
            `
            <h3>${action}</h3>
            <pre>${result}</pre>
            `;

    } catch (error) {

        output.innerHTML =
            `
            <h3>Error</h3>
            <pre>
            Failed To Generate Artifact
            </pre>
            `;
    }

    loadingSection.style.display =
        "none";

    document
        .querySelectorAll(
            ".artifact-btn")
        .forEach(
            btn => btn.disabled = false);
}

async function generateCustomPrompt() {

    const prompt =
        document.getElementById(
            "customPrompt")
            .value;

    if (!prompt) {

        alert(
            "Please enter a prompt");

        return;
    }

    generateArtifact(
        prompt);
}